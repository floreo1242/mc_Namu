package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.GuildConfig
import com.namu.core.rpg.guild.model.entity.GuildUpgradeValue
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class GuildConfigFileDataSource(
        dataFolder: File
) : GuildConfigDataSource {

    private val file = File(dataFolder, "config.yml")
    private val config = YamlConfiguration.loadConfiguration(file)
    private var guildConfig = GuildConfig((1..10).map { GuildUpgradeValue(it * 1000, it * 1000) }, 10, 5, 30)

    init {
        reloadGuildConfigs()
    }

    override fun getGuildConfig(): GuildConfig {
        return guildConfig
    }

    override fun editGuildConfig(guildConfig: GuildConfig) {
        config.set("data", guildConfig)
    }

    override fun reloadGuildConfigs() {
        if (!config.contains("data")) {
            config.set("data", guildConfig)
            config.save(file)
        }
        guildConfig = config.get("data") as GuildConfig
    }

}