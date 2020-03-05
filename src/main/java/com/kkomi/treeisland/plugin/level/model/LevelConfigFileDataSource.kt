package com.kkomi.treeisland.plugin.level.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig
import com.kkomi.treeisland.plugin.stat.model.StatConfigDataSource
import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class LevelConfigFileDataSource(
        val dataFolder: File
) : LevelConfigDataSource {

    private var config: LevelConfig? = null
    private var configFile = File(dataFolder, "config.yml")
    private var configuration = YamlConfiguration.loadConfiguration(configFile)

    init {
        if (!configuration.getKeys(false).contains("config")) {
            configuration.set("config", LevelConfig(listOf(100, 200, 300)))
            configuration.save(configFile)
        }
    }

    override fun getLevelConfig(): LevelConfig {
        if (config == null) reloadLevelConfig()
        return config!!
    }

    override fun setLevelConfig(levelConfig: LevelConfig) {
        configuration.set("config", levelConfig)
        configuration.save(configFile)
    }

    override fun reloadLevelConfig() {
        configFile = File(dataFolder, "config.yml")
        configuration = YamlConfiguration.loadConfiguration(configFile)
        config = configuration.get("config") as LevelConfig
    }

}