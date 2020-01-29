package com.kkomi.treeisland.plugin.stat.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("StatConfig")
data class StatConfig(
        var levelUpStat: Int,
        val statLimit: Int,
        val strPointByValue: Double,
        val dexPointByValue: Double,
        val vitPointByValue: Double,
        val intPointByValue: Double,
        val agiPointByValue: Double
) : ConfigurationSerializable {
    companion object {
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
                "strPointByValue" to dexPointByValue,
                "dexPointByValue" to dexPointByValue,
                "vitPointByValue" to dexPointByValue,
                "intPointByValue" to dexPointByValue,
                "agiPointByValue" to agiPointByValue
        )
    }
}