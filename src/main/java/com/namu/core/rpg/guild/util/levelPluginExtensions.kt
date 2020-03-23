package com.namu.core.rpg.guild.util

import com.namu.core.rpg.guild.model.PlayerGuildRepository
import com.namu.core.rpg.guild.model.entity.PlayerGuild
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerGuild: PlayerGuild
    get() {
        val playerGuild = PlayerGuildRepository.getPlayerGuild(uniqueId.toString())
        if (playerGuild == null) {
            val createdPlayerGuild = PlayerGuild(uniqueId.toString(), "")
            PlayerGuildRepository.createPlayerGuild(createdPlayerGuild)
            return createdPlayerGuild
        }
        return playerGuild
    }

val OfflinePlayer.playerGuild: PlayerGuild?
    get() {
        return PlayerGuildRepository.getPlayerGuild(uniqueId.toString())
    }

fun PlayerGuild.edit() {
    PlayerGuildRepository.editPlayerGuild(this)
    PlayerGuildRepository.savePlayerGuild(this)
}