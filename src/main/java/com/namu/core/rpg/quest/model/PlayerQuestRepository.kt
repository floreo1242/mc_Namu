package com.namu.core.rpg.quest.model

import com.namu.core.MainCore
import com.namu.core.rpg.quest.model.entity.PlayerQuest
import java.io.File

object PlayerQuestRepository {

    private val playerQuestDataSource = PlayerQuestFileDataSource(File("${MainCore.questPlugin.dataFolder}/players"), PlayerQuest::class.java)

    fun getPlayerQuest(uuid: String): PlayerQuest? {
        return playerQuestDataSource.getPlayerQuest(uuid)
    }

    fun getPlayerQuestList(): List<PlayerQuest> {
        return playerQuestDataSource.getPlayerQuestList()
    }

    fun createPlayerQuest(playerQuest: PlayerQuest) {
        playerQuestDataSource.createPlayerQuest(playerQuest)
    }

    fun editPlayerQuest(playerQuest: PlayerQuest) {
        playerQuestDataSource.editPlayerQuest(playerQuest)
    }

    fun savePlayerQuest(playerQuest: PlayerQuest) {
        playerQuestDataSource.savePlayerQuest(playerQuest)
    }

    fun removePlayerQuest(name: String) {
        playerQuestDataSource.removePlayerQuest(name)
    }

    fun reloadPlayerQuest() {
        playerQuestDataSource.reloadPlayerQuest()
    }

}