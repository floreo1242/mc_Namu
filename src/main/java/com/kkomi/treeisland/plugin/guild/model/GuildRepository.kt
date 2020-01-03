package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildInviteRequest
import java.io.File

object GuildRepository {

    private val guildDataSource = GuildFileDataSource(File(Treeisland.guildPlugin.dataFolder.path + "/guilds"), Guild::class.java)

    fun getGuild(name: String): Guild? {
        return guildDataSource.getGuild(name)
    }

    fun getGuildList(): List<Guild> {
        return guildDataSource.getGuildList()
    }

    fun addGuild(guild: Guild) {
        guildDataSource.addGuild(guild)
    }

    fun editGuild(guild: Guild) {
        guildDataSource.editGuild(guild)
    }

    fun removeGuild(name: String) {
        guildDataSource.removeGuild(name)
    }

    fun reloadGuilds() {
        guildDataSource.reloadGuilds()
    }

}