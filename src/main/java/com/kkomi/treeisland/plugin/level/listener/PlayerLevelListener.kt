package com.kkomi.treeisland.plugin.level.listener

import com.kkomi.treeisland.plugin.level.api.PlayerExpGetEvent
import com.kkomi.treeisland.plugin.level.api.PlayerLevelUpEvent
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerLevelListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerLevelRepository.getPlayerLevel(uuid) == null) {
            PlayerLevelRepository.addPlayerLevel(PlayerLevel(uuid, 0, 1))
        }
    }

    @EventHandler
    fun onPlayerLevelUpEvent(event: PlayerLevelUpEvent) {
        val level = event.playerInfo.levelInfo.level
        event.playerInfo.sendInfoMessage("레벨업을 하였습니다. [ &6%d&f -> &6%d&f ]".format(level - 1, level))
    }

    @EventHandler
    fun onPlayerExpGetEvent(event: PlayerExpGetEvent) {
        val playerInfo = event.playerInfo

        // 플레이어 레벨이 최대라면 중지
        if (playerInfo.levelInfo.level == LevelConfigRepository.getLevelConfig().getMaxLevel()) {
            event.isCancelled = true
            return
        }

        // 레벨업 확인 체크
        PlayerLevelRepository.checkLevelUp(playerInfo)

        playerInfo.player.apply {
            // 레벨은 플레이어 레벨
            level = playerInfo.levelInfo.level
            // 현재 경험치 / 레벨 테이블 경험치
            exp = (playerInfo.levelInfo.exp.toFloat() / LevelConfigRepository.getLevelConfig().getExpByLevel(playerInfo.levelInfo.level).toFloat())
        }

    }

}