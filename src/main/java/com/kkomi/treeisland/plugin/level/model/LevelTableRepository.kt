package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.Treeisland

object LevelTableRepository {

    private val levelTableDataSource = LevelTableFileDataSource(Treeisland.levelPlugin.dataFolder)

    fun getLevelExp(level: Int): Int {
        return levelTableDataSource.getLevelExp(level)
    }

    fun getMaxLevel(): Int {
        return levelTableDataSource.getMaxLevel()
    }

}