package com.namu.core.rpg.stat.model.entity

import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("StatConfig")
data class StatConfig(
        var levelUpStat: Int = 2,
        val pointByValue: Map<StatType, Double>,
        val maxValue: Map<StatType, Int>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): StatConfig {
            return StatConfig(
                    data["levelUpStat"] as Int,
                    (data["pointByValue"] as Map<String, Double>).mapKeys { StatType.valueOf(it.key) },
                    (data["maxValue"] as Map<String, Int>).mapKeys { StatType.valueOf(it.key) }
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelUpStat" to levelUpStat,
                "pointByValue" to pointByValue.mapKeys { it.key.toString() },
                "maxValue" to maxValue.mapKeys { it.key.toString() }
        )
    }
}