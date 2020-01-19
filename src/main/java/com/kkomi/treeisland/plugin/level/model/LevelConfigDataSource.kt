package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig

interface LevelConfigDataSource {

    fun getLevelConfig(): LevelConfig

    fun editLevelConfig(levelConfig : LevelConfig)

    fun reloadLevelConfig()

}