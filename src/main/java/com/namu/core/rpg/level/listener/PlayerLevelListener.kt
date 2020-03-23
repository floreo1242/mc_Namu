package com.namu.core.rpg.level.listener

import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.level.api.PlayerExpGetEvent
import com.namu.core.rpg.level.api.PlayerLevelUpEvent
import com.namu.core.rpg.level.model.LevelConfigRepository
import com.namu.core.rpg.level.model.PlayerLevelRepository
import com.namu.core.rpg.level.model.entity.PlayerLevel
import com.namu.core.rpg.level.util.playerLevel
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerLevelListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerLevelRepository.getPlayerLevel(uuid) == null) {
            PlayerLevelRepository.createPlayerLevel(PlayerLevel(uuid, 0, 1))
        }
    }

    @EventHandler
    fun onPlayerLevelUpEvent(event: PlayerLevelUpEvent) {
        val level = event.player.playerLevel.level
        event.player.sendInfoMessage("레벨업을 하였습니다. [ &6%d&f -> &6%d&f ]".format(level - 1, level))
    }

    @EventHandler
    fun onPlayerExpGetEvent(event: PlayerExpGetEvent) {
        val player = event.player

        // 플레이어 레벨이 최대라면 중지
        if (event.player.playerLevel.level == LevelConfigRepository.getLevelConfig().getMaxLevel()) {
            event.isCancelled = true
            return
        }

        // 레벨업 확인 체크
        PlayerLevelRepository.checkLevelUp(player)

        player.apply {
            // 레벨은 플레이어 레벨
            level = event.player.playerLevel.level
            // 현재 경험치 / 레벨 테이블 경험치
            exp = (event.player.playerLevel.exp.toFloat() / LevelConfigRepository.getLevelConfig().getExpByLevel(event.player.playerLevel.level).toFloat())
        }

    }

}