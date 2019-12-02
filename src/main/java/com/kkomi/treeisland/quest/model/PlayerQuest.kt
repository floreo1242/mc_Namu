package com.kkomi.treeisland.quest.model

import com.kkomi.library.extension.getSingle
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.library.extension.toMap
import com.kkomi.treeisland.quest.PlayerQuestManager
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.*

data class PlayerQuest(
        val manager: PlayerQuestManager,
        val uuid: String,
        val completeQuestList: MutableList<String>,
        val inProgressQuestList: MutableMap<String, Int>
) {

    val questCompleteCheckMessageList: MutableList<String> = mutableListOf()

    constructor(manager: PlayerQuestManager, config: YamlConfiguration) : this(
            manager,
            config.getString("uuid"),
            config.getStringList("completeQuestList"),
            config.getStringList("inProgressQuestList").toMap(",")
    )

    fun checkQuestAmount(
            questAction: QuestAction,
            countingCondition: (quest: Quest) -> Boolean
    ) {
        println(questCompleteCheckMessageList)

        val player = Bukkit.getPlayer(UUID.fromString(uuid))
        val inventoryMap = player.inventory.storageContents.toList().toMap()

        inProgressQuestList.keys
                .map { QuestPlugin.questManager.getQuest(it)!! }
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

    val file: File
        get() {
            manager.folder.mkdir()
            return File(manager.folder, "$uuid${manager.EXT}")
        }

    fun save() {
        val config = YamlConfiguration.loadConfiguration(file)
        config.set("uuid", uuid)
        config.set("completeQuestList", completeQuestList)
        config.set("inProgressQuestList", inProgressQuestList.map { "${it.key},${it.value}" }.toList())
        config.save(file)
    }

    fun remove() {
        manager.justRemove(uuid)
        file.delete()
    }

}