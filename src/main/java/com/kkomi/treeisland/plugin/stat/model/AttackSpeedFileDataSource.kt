package com.kkomi.treeisland.plugin.stat.model

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class AttackSpeedFileDataSource(
        val dataFolder: File
) : AttackSpeedDataSource {
    override fun getAttackSpeed(subType: String): Double {
        val config = YamlConfiguration.loadConfiguration(File(dataFolder, "AttackSpeed.yml"))
        return config.getDouble(subType, 0.0)
    }
}