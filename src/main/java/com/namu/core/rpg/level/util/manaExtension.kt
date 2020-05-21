package com.namu.core.rpg.level.util

import com.namu.core.rpg.level.model.PlayerLevelRepository
import com.namu.core.rpg.level.model.entity.PlayerLevel
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerLevel: PlayerLevel
    get() {
        val playerLevel = PlayerLevelRepository.getPlayerLevel(uniqueId.toString())
        if (playerLevel == null) {
            val createdPlayerLevel = PlayerLevel(uniqueId.toString(), 0, 1)
            PlayerLevelRepository.createPlayerLevel(createdPlayerLevel)
            return createdPlayerLevel
        }
        return playerLevel
    }

val OfflinePlayer.playerLevel: PlayerLevel?
    get() {
        return PlayerLevelRepository.getPlayerLevel(uniqueId.toString())
    }

fun PlayerLevel.edit() {
    PlayerLevelRepository.editPlayerLevel(this)
    PlayerLevelRepository.savePlayerLevel(this)
}