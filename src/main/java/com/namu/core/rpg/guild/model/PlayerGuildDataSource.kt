package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.PlayerGuild

interface PlayerGuildDataSource {

    fun getPlayerGuild(uuid: String): PlayerGuild?

    fun createPlayerGuild(playerGuild: PlayerGuild)

    fun editPlayerGuild(playerGuild: PlayerGuild)

    fun removePlayerGuild(uuid: String)

    fun reloadPlayerGuilds()

    fun getPlayerGuildList(): List<PlayerGuild>

    fun savePlayerGuild(playerGuild: PlayerGuild)
}