package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.GuildConfig

interface GuildConfigDataSource {

    fun getGuildConfig(): GuildConfig

    fun editGuildConfig(guildConfig: GuildConfig)

    fun reloadGuildConfigs()

}