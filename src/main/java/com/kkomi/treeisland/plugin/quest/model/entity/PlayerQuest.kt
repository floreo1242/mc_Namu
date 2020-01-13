package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.library.extension.toMap
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import java.util.*

@SerializableAs("PlayerQuest")
data class PlayerQuest(
        val uuid: String,
        val completeQuestList: MutableList<String>,
        val inProgressQuestList: MutableMap<String, List<PlayerQuestObjective>>
) : ConfigurationSerializable {

    private val questCompleteCheckMessageList: MutableList<String> = mutableListOf()

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerQuest {
            return PlayerQuest(
                    data["uuid"] as String,
                    data["completeQuestList"] as MutableList<String>,
                    data["inProgressQuestList"] as MutableMap<String, List<PlayerQuestObjective>>
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

        inProgressQuestList
                .forEach { (questName, questObjectiveList) ->

                    questObjectiveList
                            .forEachIndexed { index, playerQO ->

                                if (playerQO.action != questAction) {
                                    return@forEachIndexed
                                }

                                if (!condition.invoke(playerQO)) {
                                    return@forEachIndexed
                                }

                                if (playerQO.isComplete()) {
                                    return@forEachIndexed
                                }


                                val playerQuestObjectiveList = inProgressQuestList[questName]!!.toMutableList()
                                val playerQuestObjective = playerQuestObjectiveList[index]

                                playerQuestObjective.amount += if (playerQuestObjective.action == QuestAction.FARMING_ITEM) {
                                    inventoryMap
                                            .filter { item -> item.key.hasItemMeta() }
                                            .filter { item -> item.key.itemMeta.hasDisplayName() }
                                            .filter { item -> item.key.itemMeta.displayName == OtherItemRepository.getItem(playerQuestObjective.target)?.toItemStack()?.getDisplay() ?: "" }
                                            .count()
                                } else {
                                    1
                                }

                                if (playerQuestObjective.amount >= playerQuestObjective.targetAmount) {
                                    playerQuestObjective.amount = playerQuestObjective.targetAmount
                                }

                                playerQuestObjectiveList[index] = playerQuestObjective
                                inProgressQuestList[questName] = playerQuestObjectiveList
                            }

                    if (!questCompleteCheckMessageList.contains(questName) && questObjectiveList.map { it.isComplete() }.find { !it } != false) {
                        player.sendInfoMessage("$questName 퀘스트를 완료하였습니다!")
                        questCompleteCheckMessageList.add(questName)
                    }
                }
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
        }
    }

    fun completeQuest(quest: Quest) {
        inProgressQuestList.remove(quest.name)
        questCompleteCheckMessageList.remove(quest.name)
        if (quest.type == QuestType.NORMAL) {
            completeQuestList.add(quest.name)
        }
    }

}