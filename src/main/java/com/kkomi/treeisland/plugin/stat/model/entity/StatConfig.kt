package com.kkomi.treeisland.plugin.stat.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("StatConfig")
data class StatConfig(
        var levelUpStat: Int,
        val statLimit: Int
) : ConfigurationSerializable {
    companion object {
        fun deserialize(data: Map<String, Any>): StatConfig {
            return StatConfig(
                    data["levelUpStat"] as Int,
                    data["statLimit"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelUpStat" to levelUpStat,
                "statLimit" to statLimit
        )
    }
}