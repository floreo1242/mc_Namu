package com.namu.core.rpg.level.model

import com.namu.core.rpg.level.model.entity.LevelConfig

interface LevelConfigDataSource {

    fun getLevelConfig(): LevelConfig

    fun setLevelConfig(levelConfig : LevelConfig)

    fun reloadLevelConfig()

}