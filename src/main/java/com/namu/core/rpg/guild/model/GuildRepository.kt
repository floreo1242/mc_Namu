package com.namu.core.rpg.guild.model

import com.namu.core.MainCore
import com.namu.core.rpg.guild.model.entity.Guild
import java.io.File

object GuildRepository {

    private val guildDataSource = GuildFileDataSource(File(MainCore.guildPlugin.dataFolder.path + "/guilds"), Guild::class.java)

    fun getGuild(name: String): Guild? {
        return guildDataSource.getGuild(name)
    }

    fun getGuildList(): List<Guild> {
        return guildDataSource.getGuildList()
    }

    fun createGuild(guild: Guild) {
        guildDataSource.createGuild(guild)
    }

    fun editGuild(guild: Guild) {
        guildDataSource.editGuild(guild)
    }

    fun saveGuild(guild:Guild) {
        guildDataSource.saveGuild(guild)
    }

    fun removeGuild(name: String) {
        guildDataSource.removeGuild(name)
    }

    fun reloadGuild() {
        guildDataSource.reloadGuilds()
    }

}