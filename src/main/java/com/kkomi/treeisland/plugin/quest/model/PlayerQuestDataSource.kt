package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest

interface PlayerQuestDataSource {

    fun getPlayerQuest(uuid: String): PlayerQuest?

    fun getPlayerQuestList(): List<PlayerQuest>

    fun addPlayerQuest(playerQuest: PlayerQuest)

    fun editPlayerQuest(playerQuest: PlayerQuest)

    fun removePlayerQuest(uuid: String)

}