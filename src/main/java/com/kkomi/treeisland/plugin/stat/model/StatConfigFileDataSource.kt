package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class StatConfigFileDataSource(
        val dataFolder: File
) : StatConfigDataSource {

    private var config: StatConfig? = null
    private var configFile = File(dataFolder, "config.yml")
    private var configuration = YamlConfiguration.loadConfiguration(configFile)

    init {
        if (!configuration.getKeys(false).contains("config")) {
            configuration.set("config", StatConfig())
            configuration.save(configFile)
        }
    }

    override fun getStatConfig(): StatConfig {
        if (config == null) reloadStatConfig()
        return config!!
    }

    override fun setStatConfig(statConfig: StatConfig) {
        configuration.set("config", statConfig)
        configuration.save(configFile)
    }

    override fun reloadStatConfig() {
        configFile = File(dataFolder,"config.yml")
        configuration = YamlConfiguration.loadConfiguration(configFile)
        config = configuration.get("config") as StatConfig
    }

}