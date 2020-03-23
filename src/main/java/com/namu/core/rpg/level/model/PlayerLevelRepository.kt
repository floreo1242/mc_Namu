package com.namu.core.rpg.level.model

import com.namu.core.MainCore
import com.namu.core.rpg.level.api.PlayerLevelUpEvent
import com.namu.core.rpg.level.model.entity.PlayerLevel
import com.namu.core.rpg.level.util.playerLevel
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File

object PlayerLevelRepository {

    private val playerLevelDataSource = PlayerLevelFileDataSource(File("${MainCore.levelPlugin.dataFolder.path}/players"), PlayerLevel::class.java)

    fun getPlayerLevel(uuid: String): PlayerLevel? {
        return playerLevelDataSource.getPlayerLevel(uuid)
    }

    fun createPlayerLevel(playerLevel: PlayerLevel) {
        playerLevelDataSource.createPlayerLevel(playerLevel)
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

    fun checkLevelUp(player : Player) {
        val levelConfig = LevelConfigRepository.getLevelConfig()
        val playerLevel: PlayerLevel = player.playerLevel

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
            Bukkit.getServer().pluginManager.callEvent(PlayerLevelUpEvent(false, player))
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