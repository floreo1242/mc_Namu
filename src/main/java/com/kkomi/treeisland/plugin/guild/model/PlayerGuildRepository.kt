package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild
import java.io.File

object PlayerGuildRepository {

    private val PlayerGuildDataSource = PlayerGuildFileDataSource(File(Treeisland.guildPlugin.dataFolder.path + "/players"), PlayerGuild::class.java)

    fun getPlayerGuild(uuid: String): PlayerGuild? {
        return PlayerGuildDataSource.getPlayerGuild(uuid)
    }

    fun getPlayerGuildList(): List<PlayerGuild> {
        return PlayerGuildDataSource.getPlayerGuildList()
    }

    fun createPlayerGuild(playerGuild: PlayerGuild) {
        PlayerGuildDataSource.createPlayerGuild(playerGuild)
    }

    fun editPlayerGuild(playerGuild: PlayerGuild) {
        PlayerGuildDataSource.editPlayerGuild(playerGuild)
    }

    fun removePlayerGuild(uuid: String) {
        PlayerGuildDataSource.removePlayerGuild(uuid)
    }

    fun savePlayerGuild(playerGuild: PlayerGuild) {
        PlayerGuildDataSource.savePlayerGuild(playerGuild)
    }

    fun reloadPlayerGuild() {
        PlayerGuildDataSource.reloadPlayerGuilds()
    }

}