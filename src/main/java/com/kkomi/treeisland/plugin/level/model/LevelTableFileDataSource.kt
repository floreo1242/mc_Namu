package com.kkomi.treeisland.plugin.level.model

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class LevelTableFileDataSource(
        val dataFolder: File
) : LevelTableDataSource {

    val file = File(dataFolder, "LevelTable.yml")
    val configuration: YamlConfiguration = YamlConfiguration.loadConfiguration(file)
    private val levelTable: MutableMap<Int, Int> = mutableMapOf()

    init {
        if (!configuration.contains("1")) {
            configuration.addDefault("1", 100)
            configuration.addDefault("2", 200)
            configuration.addDefault("3", 300)
            configuration.save(file)
        }

        configuration.getKeys(false).forEach { levelTable[it.toInt()] = configuration.getInt(it) }
    }

    override fun getLevelExp(level: Int): Int {
        return levelTable[level] ?: 0
    }

    override fun getMaxLevel(): Int {
        return levelTable.keys.max() ?: 1
    }
}