package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import java.io.File

class GuildFileDataSource(
        dataFolder: File,
        classType: Class<Guild>
) : GuildDataSource, FileDataSource<Guild>(dataFolder, classType) {

    override fun getGuildList() : List<Guild> {
        return getValueList()
    }

    override fun getGuild(name: String): Guild? {
        return getValue(name)
    }

    override fun addGuild(guild: Guild) {
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

    override fun reloadGuilds() {
        loadFiles()
    }
}