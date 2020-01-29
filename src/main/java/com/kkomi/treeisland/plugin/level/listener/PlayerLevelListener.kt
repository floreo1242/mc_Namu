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
        playerInfo.player.apply {
            if (playerInfo.levelInfo.level == LevelConfigRepository.getLevelConfig().getMaxLevel()) {
                event.isCancelled = true
                return
            }

            level = playerInfo.levelInfo.level
            exp = (playerInfo.levelInfo.exp.toFloat() / LevelConfigRepository.getLevelConfig().getExpByLevel(playerInfo.levelInfo.level).toFloat())
        }
        PlayerLevelRepository.checkLevelUp(playerInfo)
    }

}