package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.library.extension.takeItem
import com.kkomi.treeisland.library.extension.toMap
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import com.kkomi.treeisland.plugin.monster.model.entity.DropItemType
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

@SerializableAs("PlayerQuest")
data class PlayerQuest(
        val uuid: String,
        val completeQuestList: MutableList<String>,
        val inProgressQuestList: MutableMap<String, MutableList<PlayerQuestObjective>>
) : ConfigurationSerializable {

    private val questCompleteCheckMessageList: MutableList<String> = mutableListOf()

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerQuest {
            return PlayerQuest(
                    data["uuid"] as String,
                    data["completeQuestList"] as MutableList<String>,
                    data["inProgressQuestList"] as MutableMap<String, MutableList<PlayerQuestObjective>>
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "uuid" to uuid,
                "completeQuestList" to completeQuestList,
                "inProgressQuestList" to inProgressQuestList
        )
    }

    fun checkQuestAmount(
            questAction: QuestAction,
            condition: (questObjective: PlayerQuestObjective) -> Boolean
    ) {
        val player = Bukkit.getPlayer(UUID.fromString(uuid))
        val inventoryMap = player.inventory.storageContents.toList().toMap()

        // 현재 진행중인 퀘스트들을 다 확인한다.
        inProgressQuestList
                .forEach { (questName, questObjectiveList) ->
                    // 퀘스트의 목적 목록들을 다 확인한다.
                    questObjectiveList
                            .forEachIndexed { index, playerQuestObjective ->

                                // 동일한 퀘스트 조건 행위인가?
                                if (playerQuestObjective.action != questAction) {
                                    return@forEachIndexed
                                }

                                // 이미 완료한 퀘스트 조건 인가?
                                if (playerQuestObjective.isComplete()) {
                                    return@forEachIndexed
                                }

                                // 퀘스트 조건에 충족하는가?
                                if (!condition.invoke(playerQuestObjective)) {
                                    return@forEachIndexed
                                }

                                // 카운트
                                if (playerQuestObjective.action == QuestAction.FARMING_ITEM) {
                                    playerQuestObjective.amount = getIncrementCount(playerQuestObjective, inventoryMap)
                                } else {
                                    playerQuestObjective.amount += getIncrementCount(playerQuestObjective, inventoryMap)
                                }

                                // 적용
                                questObjectiveList[index] = playerQuestObjective
                                inProgressQuestList[questName] = questObjectiveList
                                PlayerQuestRepository.editPlayerQuest(this)
                            }


                    if (questObjectiveList.map { it.isComplete() }.contains(false).not()) {
                        if (!questCompleteCheckMessageList.contains(questName)) {
                            questCompleteCheckMessageList.add(questName)
                        }
                    } else {
                        questCompleteCheckMessageList.remove(questName)
                    }


                }
    }

    private fun getIncrementCount(playerQuestObjective: PlayerQuestObjective, inventoryMap: Map<ItemStack, Int>): Int {
        return if (playerQuestObjective.action == QuestAction.FARMING_ITEM) {
            var count = inventoryMap
                    .filter { item -> item.key.hasItemMeta() }
                    .filter { item -> item.key.itemMeta.hasDisplayName() }
                    .filter { item -> item.key.itemMeta.displayName == OtherItemRepository.getItem(playerQuestObjective.target)?.toItemStack()?.getDisplay() ?: "" }
                    .map { it.value }
                    .sum()

            if (count >= playerQuestObjective.targetAmount) {
                count = playerQuestObjective.targetAmount
            }

            return count
        } else {
            1
        }
    }

    fun isAvailableQuest(quest: Quest, playerLevel: PlayerLevel): Boolean {

        if (playerLevel.level < quest.limitLv) {
            return false
        }

        if (quest.beforeQuest != "" && !completeQuestList.contains(quest.beforeQuest)) {
            return false
        }

        return true
    }

    fun throwUpQuest(quest: Quest) {
        inProgressQuestList.remove(quest.name)
    }

    fun acceptQuest(quest: Quest) {
        inProgressQuestList[quest.name] = quest.questObjectiveList.map { questObjective ->
            PlayerQuestObjective(
                    questObjective.action,
                    0,
                    questObjective.amount,
                    questObjective.target
            )
        }.toMutableList()
    }

    fun isCompletedQuest(quest: Quest): Boolean {
        return questCompleteCheckMessageList.contains(quest.name)
    }

    fun completeQuest(quest: Quest) {
        inProgressQuestList.remove(quest.name)
        questCompleteCheckMessageList.remove(quest.name)
        if (quest.type == QuestType.NORMAL) {
            completeQuestList.add(quest.name)
        }
    }

    fun takeQuestItems(player: Player, quest: Quest) {
        quest.questObjectiveList
                .filter { it.action == QuestAction.FARMING_ITEM }
                .forEach { questObjective ->
                    player.inventory.takeItem(OtherItemRepository.getItem(questObjective.target)!!.toItemStack(), questObjective.amount)
                }
    }

    fun receiveRewards(player: Player, quest: Quest) {
        player.inventory.addItem(
                *quest.reward.items
                        .map {
                            it.toItemStack()
                        }.toTypedArray()
        )

        player.getPlayerInfo().apply {
            levelInfo.exp += quest.reward.exp
            PlayerLevelRepository.checkLevelUp(this)
        }.editPlayerInfo()

        if (quest.reward.command == "") {
            return
        }

        if (!player.isOp) {
            player.isOp = true
            player.performCommand(quest.reward.command)
            player.isOp = false
        } else {
            player.performCommand(quest.reward.command)
        }
    }

}