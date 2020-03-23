package com.namu.core.utility.post.util

import com.namu.core.utility.post.model.PlayerPostBoxRepository
import com.namu.core.utility.post.model.entity.PlayerPostBox
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerPostBox: PlayerPostBox
    get() {
        val playerPostBox = PlayerPostBoxRepository.getPlayerPostBox(uniqueId.toString())
        if (playerPostBox == null) {
            val createdPlayerPostBox = PlayerPostBox(mutableListOf(), uniqueId.toString())
            PlayerPostBoxRepository.createPlayerPostBox(createdPlayerPostBox)
            return createdPlayerPostBox
        }
        return playerPostBox
    }

val OfflinePlayer.playerPostBox: PlayerPostBox?
    get() {
        return PlayerPostBoxRepository.getPlayerPostBox(uniqueId.toString())
    }

fun PlayerPostBox.edit() {
    PlayerPostBoxRepository.editPlayerPostBox(this)
    PlayerPostBoxRepository.savePlayerPostBox(this)
}