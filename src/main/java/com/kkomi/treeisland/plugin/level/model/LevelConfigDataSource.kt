package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig

interface LevelConfigDataSource {

    fun getLevelConfig(): LevelConfig

    fun setLevelConfig(levelConfig : LevelConfig)

    fun reloadLevelConfig()

}