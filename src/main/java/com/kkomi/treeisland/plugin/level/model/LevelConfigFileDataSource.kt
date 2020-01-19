package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class LevelConfigFileDataSource(
        dataFolder: File
) : LevelConfigDataSource {

    private var config : LevelConfig? = null
    private val configFile = File(dataFolder,"config.yml")
    private val configuration = YamlConfiguration.loadConfiguration(configFile)
    
    override fun getLevelConfig(): LevelConfig {
        if(config == null) reloadLevelConfig()
        return config!!
    }

    override fun editLevelConfig(levelConfig : LevelConfig) {
        configuration.set("config",config)
        configuration.save(configFile)
    }

    override fun reloadLevelConfig() {
        config = configuration.get("config") as LevelConfig
    }

}