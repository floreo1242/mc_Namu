package com.namu.core.rpg.guild.model

import com.namu.core.MainCore
import com.namu.core.rpg.guild.model.entity.Guild
import java.io.File

/**
 * 데이터 CRUD 작업의 브릿지 역활
 *
 * Command, Inventory, Listener < - > Data Source
 *
 * Events <-> FileDataSource
 *
 * GuildFileDataSource -> 56 Class
 * GuildDBDataSource ->
 */
object GuildRepository {

    private val guildDataSource : GuildDataSource = GuildFileDataSource(File(MainCore.guildPlugin.dataFolder.path + "/guilds"), Guild::class.java)

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