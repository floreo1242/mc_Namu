package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild
import java.io.File

class PlayerGuildFileDataSource(
        dataFolder: File,
        classType: Class<PlayerGuild>
) : PlayerGuildDataSource, FileDataSource<PlayerGuild>(dataFolder, classType) {

    override fun getPlayerGuildList(): List<PlayerGuild> {
        return getValueList()
    }

    override fun getPlayerGuild(uuid: String): PlayerGuild? {
        return getValue(uuid)
    }

    override fun createPlayerGuild(playerGuild: PlayerGuild) {
        setValue(playerGuild.uuid, playerGuild)
    }

    override fun editPlayerGuild(playerGuild: PlayerGuild) {
        setValue(playerGuild.uuid, playerGuild)
    }

    override fun removePlayerGuild(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun savePlayerGuild(playerGuild: PlayerGuild) {
        saveFile(playerGuild.uuid, playerGuild)
    }

    override fun reloadPlayerGuilds() {
        loadFiles()
    }
}