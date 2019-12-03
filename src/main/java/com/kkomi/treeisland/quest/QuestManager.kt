package com.kkomi.treeisland.quest

import com.kkomi.library.ObjectManager
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.*
import java.util.logging.Logger

class QuestManager(folder: File, logger: Logger) : ObjectManager<Quest>(folder, logger) {

    override fun loadData() {
        val questByName = TreeMap<String, Quest>(String.CASE_INSENSITIVE_ORDER)
        getFolderFiles().filter { !it.name.contains("keyword") }.forEach { file ->
            val questName = file.name.substring(0, file.name.length - EXTlen)
            try {
                val config = YamlConfiguration.loadConfiguration(file)
                val quest = Quest(this, config)
                questByName[quest.name] = quest
            } catch (exception: Exception) {
                logger.info("Failed to load from $questName cause : ")
                exception.printStackTrace()
            }
        }
        println("Load File Count : ${questByName.keys.count()}")
        this.objectByName = questByName
    }

    fun createQuest(name: String): Quest {
        val quest = Quest(this, name)
        objectByName[quest.name] = quest
        quest.save()
        this.objectList = null
        return quest
    }

    fun getQuest(QuestName: String): Quest? = objectByName[QuestName]

    fun getQuestToTitle(questTitle: String): Quest? = questList.find { it.title == questTitle }

    val questNames: Set<String> = keys!!

    val questList: List<Quest> = objectList!!

    val quests: Collection<Quest> = objects!!
}