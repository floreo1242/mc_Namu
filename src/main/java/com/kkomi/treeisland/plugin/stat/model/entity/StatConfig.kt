package com.kkomi.treeisland.plugin.stat.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("StatConfig")
data class StatConfig(
        var levelUpStat: Int = 2,
        val statLimit: Int = 160,
        val strPointByValue: Double = 0.5,
        val dexPointByValue: Double = 0.15,
        val vitPointByValue: Double = 0.3,
        val intPointByValue: Double = 1.0,
        val agiPointByValue: Double = 0.2
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): StatConfig {
            return StatConfig(
                    data["levelUpStat"] as Int,
                    data["statLimit"] as Int,
                    data["strPointByValue"] as Double,
                    data["dexPointByValue"] as Double,
                    data["vitPointByValue"] as Double,
                    data["intPointByValue"] as Double,
                    data["agiPointByValue"] as Double
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelUpStat" to levelUpStat,
                "statLimit" to statLimit,
                "strPointByValue" to strPointByValue,
                "dexPointByValue" to dexPointByValue,
                "vitPointByValue" to vitPointByValue,
                "intPointByValue" to intPointByValue,
                "agiPointByValue" to agiPointByValue
        )
    }
}