package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.plugin.quest.model.entity.Quest

interface QuestDataSource {

    fun getQuest(name: String): Quest?

    fun getQuestList(): List<Quest>

    fun addQuest(quest: Quest)

    fun editQuest(quest: Quest)

    fun removeQuest(name: String)

    fun reloadQuests()

}