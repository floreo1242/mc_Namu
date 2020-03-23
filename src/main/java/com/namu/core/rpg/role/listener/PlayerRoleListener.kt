package com.namu.core.rpg.role.listener

import com.namu.core.rpg.role.model.PlayerRoleRepository
import com.namu.core.rpg.role.model.entity.PlayerRole
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerRoleListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()
        val playerRole = PlayerRoleRepository.getPlayerRole(uuid)
        if (playerRole == null) {
            PlayerRoleRepository.createPlayerRole(PlayerRole(uuid, "모험가"))
        }
    }
}