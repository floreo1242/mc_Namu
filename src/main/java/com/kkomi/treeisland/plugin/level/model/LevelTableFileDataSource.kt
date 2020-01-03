package com.kkomi.treeisland.plugin.level.model

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class LevelTableFileDataSource(
        val dataFolder: File
) : LevelTableDataSource {

    val configuration: YamlConfiguration = YamlConfiguration.loadConfiguration(File(dataFolder, "LevelTable.yml"))
    private val levelTable: MutableMap<Int, Int> = mutableMapOf()

    init {
        configuration.getKeys(false).forEach { levelTable[it.toInt()] = configuration.getInt(it) }
    }

    override fun getLevelExp(level: Int): Int {
        return levelTable[level] ?: 0
    }

    override fun getMaxLevel(): Int {
        return levelTable.keys.max() ?: 1
    }
}