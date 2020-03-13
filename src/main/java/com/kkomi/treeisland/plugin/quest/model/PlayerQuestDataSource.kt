package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest

interface PlayerQuestDataSource {

    fun getPlayerQuest(uuid: String): PlayerQuest?

    fun getPlayerQuestList(): List<PlayerQuest>

    fun createPlayerQuest(playerQuest: PlayerQuest)

    fun editPlayerQuest(playerQuest: PlayerQuest)

    fun removePlayerQuest(uuid: String)

    fun reloadPlayerQuest()

    fun savePlayerQuest(playerQuest: PlayerQuest)

}