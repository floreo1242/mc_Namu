package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.plugin.guild.model.entity.Guild

interface GuildDataSource {

    fun getGuild(name: String): Guild?

    fun addGuild(guild: Guild)

    fun editGuild(guild: Guild)

    fun removeGuild(name: String)

    fun reloadGuilds()

    fun getGuildList(): List<Guild>
}