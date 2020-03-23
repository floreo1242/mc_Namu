package com.namu.core.rpg.quest.model

import com.namu.core.rpg.quest.model.entity.Quest

interface QuestDataSource {

    fun getQuest(name: String): Quest?

    fun getQuestList(): List<Quest>

    fun createQuest(quest: Quest)

    fun editQuest(quest: Quest)

    fun removeQuest(name: String)

    fun reloadQuests()

    fun saveQuest(quest: Quest)

}