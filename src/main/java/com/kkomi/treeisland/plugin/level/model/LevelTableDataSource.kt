package com.kkomi.treeisland.plugin.level.model

interface LevelTableDataSource {

    fun getLevelExp(level: Int): Int

    fun getMaxLevel() : Int

}