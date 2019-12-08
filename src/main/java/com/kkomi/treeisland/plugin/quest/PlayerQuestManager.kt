package com.kkomi.treeisland.plugin.quest

import com.kkomi.treeisland.library.ObjectManager
import com.kkomi.treeisland.plugin.quest.model.PlayerQuest
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.*
import java.util.logging.Logger

class PlayerQuestManager(folder: File, logger: Logger) : ObjectManager<PlayerQuest>(folder, logger) {

    override fun loadData() {
        val questPlayerByUUID = TreeMap<String, PlayerQuest>(String.CASE_INSENSITIVE_ORDER)
        getFolderFiles().filter { !it.name.contains("keyword") }.forEach { file ->
            val questPlayerUUID = file.name.substring(0, file.name.length - EXTlen)
            try {
                val config = YamlConfiguration.loadConfiguration(file)
                val playerQuest = PlayerQuest(this, config)
                questPlayerByUUID[playerQuest.uuid] = playerQuest
            } catch (exception: Exception) {
                logger.info("Failed to load from $questPlayerUUID cause : ")
                exception.printStackTrace()
            }
        }
        println("Load File Count : ${questPlayerByUUID.keys.count()}")
        this.objectByName = questPlayerByUUID
    }

    fun createQuestPlayer(uuid: String): PlayerQuest {
        val playerQuest = PlayerQuest(this, uuid, mutableListOf(), mutableMapOf())
        objectByName[playerQuest.uuid] = playerQuest
        playerQuest.save()
        this.objectList = null
        return playerQuest
    }

    fun getPlayerQuest(QuestPlayerName: String): PlayerQuest? = objectByName[QuestPlayerName]

    val playerQuestUUIDs: Set<String> = keys!!

    val playerQuestList: List<PlayerQuest> = objectList!!

    val playerQuests: Collection<PlayerQuest> = objects!!
}