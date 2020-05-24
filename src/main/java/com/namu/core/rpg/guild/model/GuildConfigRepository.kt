package com.namu.core.rpg.guild.model

import com.namu.core.MainCore
import com.namu.core.rpg.guild.model.entity.GuildUpgradeValue
import java.io.File

object GuildConfigRepository {

    private val guildConfigDataSource = GuildConfigFileDataSource(File(MainCore.guildPlugin.dataFolder.path + "/guild"))

    fun getUpgradeValue(level: Int): GuildUpgradeValue {
        return guildConfigDataSource.getGuildConfig().upgradeValue[level - 1]
    }

    fun getDefaultMaxMember(): Int {
        return guildConfigDataSource.getGuildConfig().defaultMaxMember
    }

    fun getUpgradeMemberCount(): Int {
        return guildConfigDataSource.getGuildConfig().upgradeMemberCount
    }
    
    fun getInviteTime() : Int {
        return guildConfigDataSource.getGuildConfig().inviteTime
    }

    fun reloadGuildConfigs() {
        guildConfigDataSource.reloadGuildConfigs()
    }

}