package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.library.extension.getSingle
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.library.extension.toMap
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import java.util.*

@SerializableAs("PlayerQuest")
data class PlayerQuest(
        val uuid: String,
        val completeQuestList: MutableList<String>,
        val inProgressQuestList: MutableMap<String, Int>
) : ConfigurationSerializable {

    private val questCompleteCheckMessageList: MutableList<String> = mutableListOf()

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerQuest {
            return PlayerQuest(
                    data["uuid"] as String,
                    data["completeQuestList"] as MutableList<String>,
                    data["inProgressQuestList"] as MutableMap<String, Int>
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
            countingCondition: (quest: Quest) -> Boolean
    ) {

        val player = Bukkit.getPlayer(UUID.fromString(uuid))
        val inventoryMap = player.inventory.storageContents.toList().toMap()

        inProgressQuestList.keys
                .map { QuestRepository.getQuest(it)!! }
                // Current Quest Action Filter
                .filter { it.action == questAction }
                // Check Satisfy Quest Count
                .filter(countingCondition)
                .forEach {

                    inProgressQuestList[it.name] = if (questAction == QuestAction.FARMING_ITEM) {
                        (inventoryMap[it.itemStackObject.getSingle()] ?: 0)
                    } else {
                        (inProgressQuestList[it.name] ?: 0) + 1
                    }

                    if (inProgressQuestList[it.name] == it.count) {
                        // If Not Already Print Quest Success Message
                        if (!questCompleteCheckMessageList.contains(it.name)) {
                            player.sendInfoMessage("[ ${it.title} ] 퀘스트 완료!")
                            questCompleteCheckMessageList.add(it.name)
                        }
                    } else {
                        if (questCompleteCheckMessageList.contains(it.name)) {
                            questCompleteCheckMessageList.remove(it.name)
                        }
                    }
                }
    }

    fun throwUpQuest(quest: Quest) {
        inProgressQuestList.remove(quest.name)
    }

    fun acceptQuest(quest: Quest) {
        inProgressQuestList[quest.name] = 0
    }

    fun completeQuest(quest: Quest) {
        inProgressQuestList.remove(quest.name)
        questCompleteCheckMessageList.remove(quest.name)
        if (quest.type == QuestType.NORMAL) {
            completeQuestList.add(quest.name)
        }
    }

}