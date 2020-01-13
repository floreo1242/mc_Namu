package com.kkomi.treeisland.plugin.level.listener

import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
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
        event.playerInfo.sendInfoMessage("[ LEVEL UP ] %d -> %d [ LEVEL UP ]".format(event.playerInfo.levelInfo.level - 1, event.playerInfo.levelInfo.level))
    }

}