package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild

interface PlayerGuildDataSource {

    fun getPlayerGuild(uuid: String): PlayerGuild?

    fun addPlayerGuild(playerGuild: PlayerGuild)

    fun editPlayerGuild(playerGuild: PlayerGuild)

    fun removePlayerGuild(uuid: String)

    fun reloadPlayerGuilds()

    fun getPlayerGuildList(): List<PlayerGuild>
}