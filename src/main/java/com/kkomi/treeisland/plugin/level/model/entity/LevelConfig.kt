package com.kkomi.treeisland.plugin.level.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("LevelConfig")
data class LevelConfig(
        var levelExpTable: List<Int>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): LevelConfig {
            return LevelConfig(
                    data["levelExpTable"] as List<Int>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelExpTable" to levelExpTable
        )
    }

    fun getMaxLevel(): Int {
        return levelExpTable.size
    }

    fun getExpByLevel(level: Int): Int {
        return levelExpTable[level-1]
    }
}