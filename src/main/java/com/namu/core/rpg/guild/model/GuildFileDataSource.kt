package com.namu.core.rpg.guild.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.guild.model.entity.Guild
import java.io.File

class GuildFileDataSource(
        dataFolder: File,
        classType: Class<Guild>
) : GuildDataSource, FileDataSource<Guild>(dataFolder, classType) {

    override fun getGuildList(): List<Guild> {
        return getValueList()
    }

    override fun getGuild(name: String): Guild? {
        return getValue(name)
    }

    override fun createGuild(guild: Guild) {
        setValue(guild.name, guild)
        saveFile(guild.name, guild)
    }

    override fun editGuild(guild: Guild) {
        setValue(guild.name, guild)
        saveFile(guild.name, guild)
    }

    override fun removeGuild(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun saveGuild(guild: Guild) {
        saveFile(guild.name, guild)
    }

    override fun reloadGuilds() {
        loadFiles()
    }
}