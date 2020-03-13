package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import java.io.File

object QuestRepository {

    private val questDataSource = QuestFileDataSource(File("${Treeisland.questPlugin.dataFolder}/quests"), Quest::class.java)

    fun getQuest(name: String): Quest? {
        return questDataSource.getQuest(name)
    }

    fun getQuestToTitle(title: String): Quest? {
        return questDataSource.getQuestList().find { it.title == title }
    }

    fun getQuestList(): List<Quest> {
        return questDataSource.getQuestList()
    }

    fun createQuest(quest: Quest) {
        questDataSource.createQuest(quest)
    }

    fun editQuest(quest: Quest) {
        questDataSource.editQuest(quest)
    }

    fun saveQuest(quest: Quest) {
        questDataSource.saveQuest(quest)
    }

    fun removeQuest(name: String) {
        questDataSource.removeQuest(name)
    }

    fun reloadQuest() {
        questDataSource.reloadQuests()
    }

}