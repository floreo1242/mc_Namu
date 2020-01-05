package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.guild.model.entity.GuildOption
import java.io.File

object GuildOptionOptionRepository {

    private val guildOptionDataSource = GuildOptionFileDataSource(File(Treeisland.guildPlugin.dataFolder.path + "/guild"), GuildOption::class.java)

    fun getGuildOption(name: String): GuildOption? {
        return guildOptionDataSource.get
    }

    fun getGuildOptionList(): List<GuildOption> {
        return guildOptionDataSource.getGuildOptionList()
    }

    fun addGuildOption(GuildOption: GuildOption) {
        guildOptionDataSource.addGuildOption(GuildOption)
    }

    fun editGuildOption(GuildOption: GuildOption) {
        guildOptionDataSource.editGuildOption(GuildOption)
    }

    fun removeGuildOption(name: String) {
        guildOptionDataSource.removeGuildOption(name)
    }

    fun reloadGuildOptions() {
        guildOptionDataSource.reloadGuildOptions()
    }

}