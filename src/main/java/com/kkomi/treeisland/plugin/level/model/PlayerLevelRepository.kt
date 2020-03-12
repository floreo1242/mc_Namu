package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.integration.model.PlayerInfo
import com.kkomi.treeisland.plugin.level.api.PlayerLevelUpEvent
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

    fun savePlayerLevel(playerLevel: PlayerLevel) {
        playerLevelDataSource.savePlayerLevel(playerLevel)
    }

    fun checkLevelUp(playerInfo : PlayerInfo) {
        val levelConfig = LevelConfigRepository.getLevelConfig()
        val playerLevel: PlayerLevel = playerInfo.levelInfo

        // 최대 레벨이라면 중지
        if (playerLevel.level == levelConfig.getMaxLevel()) {
            playerLevel.exp = 0
            return
        }

        // 플레이어의 레벨이 레벨 테이블 보다 높다면  반복
        while (playerLevel.exp >= levelConfig.getExpByLevel(playerLevel.level)) {
            // 플레이어 경험치 감소
            playerLevel.exp -= levelConfig.getExpByLevel(playerLevel.level)
            // 플레이어 레벨 상승
            playerLevel.level++
            // 경험치 흭득 이벤트 실행
            Bukkit.getServer().pluginManager.callEvent(PlayerLevelUpEvent(false, playerInfo))
        }

        // 만약 최대 레벨이라면
        if (playerLevel.level >= levelConfig.getMaxLevel()) {
            // 경험치를 초기화
            playerLevel.exp = 0
        }

        // 수정사항 저장
        editPlayerLevel(playerLevel)
    }

    fun reloadPlayerLevel() {
        playerLevelDataSource.reloadPlayerLevel()
    }

    fun getPlayerLevelList(): List<PlayerLevel> {
        return playerLevelDataSource.getPlayerLevelList()
    }

}