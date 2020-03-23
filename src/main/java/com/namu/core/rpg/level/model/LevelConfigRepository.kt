package com.namu.core.rpg.level.model

import com.namu.core.MainCore
import com.namu.core.rpg.level.model.entity.LevelConfig

object LevelConfigRepository {

    private val levelConfigDataSource = LevelConfigFileDataSource(MainCore.levelPlugin.dataFolder)

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