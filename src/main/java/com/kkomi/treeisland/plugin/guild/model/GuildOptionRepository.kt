package com.kkomi.treeisland.plugin.guild.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.guild.model.entity.GuildUpgradeValue
import java.io.File

object GuildOptionRepository {

    private val guildOptionDataSource = GuildOptionFileDataSource(File(Treeisland.guildPlugin.dataFolder.path + "/guild"))

    fun getUpgradeValue(level: Int): GuildUpgradeValue {
        return guildOptionDataSource.getGuildOption().upgradeValue[level - 1]
    }

    fun getDefaultMaxMember(): Int {
        return guildOptionDataSource.getGuildOption().defaultMaxMember
    }

    fun getUpgradeMemberCount(): Int {
        return guildOptionDataSource.getGuildOption().upgradeMemberCount
    }

    fun reloadGuildOptions() {
        guildOptionDataSource.reloadGuildOptions()
    }

}