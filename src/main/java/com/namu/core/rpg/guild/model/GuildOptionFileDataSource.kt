package com.namu.core.rpg.guild.model

import com.namu.core.rpg.guild.model.entity.GuildOption
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class GuildOptionFileDataSource(
        dataFolder: File
) : GuildOptionDataSource {

    private val file = File(dataFolder, "config.yml")
    private val config = YamlConfiguration.loadConfiguration(file)
    private var guildOption = GuildOption(listOf(), 10, 5)

    init {
        reloadGuildOptions()
    }

    override fun getGuildOption(): GuildOption {
        return guildOption
    }

    override fun editGuildOption(guildOption: GuildOption) {
        config.set("data", guildOption)
    }

    override fun reloadGuildOptions() {
        if (!config.contains("data")) {
            config.set("data", guildOption)
            config.save(file)
        }
        guildOption = config.get("data") as GuildOption
    }

}