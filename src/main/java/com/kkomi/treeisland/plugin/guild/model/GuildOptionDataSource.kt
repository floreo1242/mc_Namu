package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.plugin.guild.model.entity.GuildOption

interface GuildOptionDataSource {

    fun getGuildOption(): GuildOption

    fun editGuildOption(GuildOption: GuildOption)

    fun reloadGuildOptions()

}