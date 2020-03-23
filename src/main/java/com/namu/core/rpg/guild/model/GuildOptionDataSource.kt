package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.GuildOption

interface GuildOptionDataSource {

    fun getGuildOption(): GuildOption

    fun editGuildOption(guildOption: GuildOption)

    fun reloadGuildOptions()

}