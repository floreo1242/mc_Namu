package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import java.io.File

object PlayerQuestRepository {

    private val playerQuestDataSource = PlayerQuestFileDataSource(File("${Treeisland.questPlugin.dataFolder}/players"), PlayerQuest::class.java)

    fun getPlayerQuest(uuid: String): PlayerQuest? {
        return playerQuestDataSource.getPlayerQuest(uuid)
    }

    fun getPlayerQuestList(): List<PlayerQuest> {
        return playerQuestDataSource.getPlayerQuestList()
    }

    fun addPlayerQuest(playerQuest: PlayerQuest) {
        playerQuestDataSource.addPlayerQuest(playerQuest)
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