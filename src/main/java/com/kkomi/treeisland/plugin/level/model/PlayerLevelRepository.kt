package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import java.io.File

object PlayerLevelRepository {

    private val playerLevelFileDataSource = PlayerLevelFileDataSource(File("${Treeisland.levelPlugin.dataFolder.path}/players"), PlayerLevel::class.java)

    fun getPlayerLevel(uuid: String): PlayerLevel? {
        return playerLevelFileDataSource.getPlayerLevel(uuid)
    }

    fun addPlayerLevel(playerLevel: PlayerLevel) {
        playerLevelFileDataSource.addPlayerLevel(playerLevel)
    }

    fun editPlayerLevel(playerLevel: PlayerLevel) {
        playerLevelFileDataSource.editPlayerLevel(playerLevel)
    }

    fun removePlayerLevel(uuid: String) {
        playerLevelFileDataSource.removePlayerLevel(uuid)
    }

    fun checkLevelUp(playerLevel: PlayerLevel, callBack: (playerLevel: PlayerLevel) -> Unit) {
        if (playerLevel.level == LevelTableRepository.getMaxLevel()) {
            return
        }

        while (playerLevel.exp >= LevelTableRepository.getLevelExp(playerLevel.level)) {
            playerLevel.exp -= LevelTableRepository.getLevelExp(playerLevel.level)
            playerLevel.level++

            if (playerLevel.level == LevelTableRepository.getMaxLevel()) {
                playerLevel.exp = 0
            }
            callBack.invoke(playerLevel)
        }

        editPlayerLevel(playerLevel)
    }

    fun reloadPlayerLevel() {
        playerLevelFileDataSource.reloadPlayerLevel()
    }

}