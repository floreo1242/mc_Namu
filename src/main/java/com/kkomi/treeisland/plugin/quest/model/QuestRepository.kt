package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.quest.model.entity.Quest

object QuestRepository {

    private val questDataSource = QuestFileDataSource(Treeisland.questPlugin.dataFolder, Quest::class.java)

    fun getQuest(name: String): Quest? {
        return questDataSource.getQuest(name)
    }

    fun getQuestToTitle(title: String): Quest? {
        return questDataSource.getQuestList().find { it.title == title }
    }

    fun getQuestList(): List<Quest> {
        return questDataSource.getQuestList()
    }

    fun addQuest(quest: Quest) {
        questDataSource.addQuest(quest)
    }

    fun editQuest(quest: Quest) {
        questDataSource.editQuest(quest)
    }

    fun removeQuest(name: String) {
        questDataSource.removeQuest(name)
    }

    fun reloadQuest() {
        questDataSource.reloadQuests()
    }

}