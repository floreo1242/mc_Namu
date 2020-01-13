package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.level.listener.PlayerLevelUpEvent
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import org.bukkit.Bukkit
import java.io.File

object PlayerLevelRepository {

    private val playerLevelDataSource = PlayerLevelFileDataSource(File("${Treeisland.levelPlugin.dataFolder.path}/players"), PlayerLevel::class.java)

    fun getPlayerLevel(uuid: String): PlayerLevel? {
        return playerLevelDataSource.getPlayerLevel(uuid)
    }

    fun addPlayerLevel(playerLevel: PlayerLevel) {
        playerLevelDataSource.addPlayerLevel(playerLevel)
    }

    fun editPlayerLevel(playerLevel: PlayerLevel) {
        playerLevelDataSource.editPlayerLevel(playerLevel)
    }

    fun removePlayerLevel(uuid: String) {
        playerLevelDataSource.removePlayerLevel(uuid)
    }

    fun checkLevelUp(playerInfo : PlayerInfo) {
        val playerLevel: PlayerLevel = playerInfo.levelInfo
        if (playerLevel.level == LevelTableRepository.getMaxLevel()) {
            return
        }

        while (playerLevel.exp >= LevelTableRepository.getLevelExp(playerLevel.level)) {
            playerLevel.exp -= LevelTableRepository.getLevelExp(playerLevel.level)
            playerLevel.level++

            if (playerLevel.level == LevelTableRepository.getMaxLevel()) {
                playerLevel.exp = 0

            }
            Bukkit.getServer().pluginManager.callEvent(PlayerLevelUpEvent(false, playerInfo))
        }

        editPlayerLevel(playerLevel)
    }

    fun reloadPlayerLevel() {
        playerLevelDataSource.reloadPlayerLevel()
    }

}