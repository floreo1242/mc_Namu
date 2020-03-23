package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.Guild

interface GuildDataSource {

    fun getGuild(name: String): Guild?

    fun createGuild(guild: Guild)

    fun editGuild(guild: Guild)

    fun removeGuild(name: String)

    fun reloadGuilds()

    fun getGuildList(): List<Guild>

    fun saveGuild(guild: Guild)

}