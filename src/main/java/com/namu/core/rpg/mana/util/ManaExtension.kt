package com.namu.core.rpg.mana.util

import com.namu.core.rpg.mana.model.PlayerManaRepository
import com.namu.core.rpg.mana.model.entity.PlayerMana
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerMana: PlayerMana
    get() {
        val playerMana = PlayerManaRepository.getPlayerMana(uniqueId.toString())
        if (playerMana == null) {
            val createdPlayerMana = PlayerMana(100, 100, uniqueId.toString())
            PlayerManaRepository.createPlayerMana(createdPlayerMana)
            return createdPlayerMana
        }
        return playerMana
    }

val OfflinePlayer.playerMana: PlayerMana?
    get() {
        return PlayerManaRepository.getPlayerMana(uniqueId.toString())
    }

fun PlayerMana.edit() {
    PlayerManaRepository.editPlayerMana(this)
    PlayerManaRepository.savePlayerMana(this)
}