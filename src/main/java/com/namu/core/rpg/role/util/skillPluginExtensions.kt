package com.namu.core.rpg.role.util

import com.namu.core.rpg.role.model.PlayerRoleRepository
import com.namu.core.rpg.role.model.entity.PlayerRole
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerRole: PlayerRole
    get() {
        val playerRole = PlayerRoleRepository.getPlayerRole(uniqueId.toString())
        if (playerRole == null) {
            val createdPlayerRole = PlayerRole(uniqueId.toString(), "")
            PlayerRoleRepository.createPlayerRole(createdPlayerRole)
            return createdPlayerRole
        }
        return playerRole
    }

val OfflinePlayer.playerRole: PlayerRole?
    get() {
        return PlayerRoleRepository.getPlayerRole(uniqueId.toString())
    }

fun PlayerRole.edit() {
    PlayerRoleRepository.editPlayerRole(this)
    PlayerRoleRepository.savePlayerRole(this)
}