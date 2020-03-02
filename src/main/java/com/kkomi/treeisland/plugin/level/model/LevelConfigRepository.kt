package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig
import java.io.File

object LevelConfigRepository {

    private val levelConfigDataSource = LevelConfigFileDataSource(Treeisland.levelPlugin.dataFolder)

    fun getLevelConfig(): LevelConfig {
        return levelConfigDataSource.getLevelConfig()
    }

    fun editLevelConfig(levelConfig : LevelConfig) {
        levelConfigDataSource.setLevelConfig(levelConfig)
    }

    fun reloadLevelConfig() {
        levelConfigDataSource.reloadLevelConfig()
    }

}