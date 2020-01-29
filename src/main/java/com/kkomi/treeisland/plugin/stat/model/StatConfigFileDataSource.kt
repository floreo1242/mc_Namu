package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class StatConfigFileDataSource(
        dataFolder: File
) : StatConfigDataSource {

    private var config: StatConfig? = null
    private val configFile = File(dataFolder, "config.yml")
    private val configuration = YamlConfiguration.loadConfiguration(configFile)

    override fun getStatConfig(): StatConfig {
        if (config == null) reloadStatConfig()
        return config!!
    }

    override fun editStatConfig(statConfig: StatConfig) {
        configuration.set("config", config)
        configuration.save(configFile)
    }

    override fun reloadStatConfig() {
        if (!configuration.contains("config")) {
            configuration.set("config", StatConfig(2, 160, 1.0, 0.5, 1.0, 1.0, 0.5))
            configuration.save(configFile)
        }
        config = configuration.get("config") as StatConfig
    }

}