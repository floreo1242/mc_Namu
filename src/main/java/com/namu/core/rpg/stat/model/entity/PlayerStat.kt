package com.namu.core.rpg.stat.model.entity

import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerStat")
data class PlayerStat(
        val uuid: String,
        val investmentStat: MutableMap<StatType, Int>,
        var leftPoint: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerStat {
            return PlayerStat(
                    data["uuid"] as String,
                    (data["investmentStat"] as Map<String, Int>).mapKeys { StatType.valueOf(it.key) } as MutableMap<StatType, Int>,
                    data["leftPoint"] as Int
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "uuid" to uuid,
                "investmentStat" to investmentStat.mapKeys { it.key.toString() },
                "leftPoint" to leftPoint
        )
    }

}

