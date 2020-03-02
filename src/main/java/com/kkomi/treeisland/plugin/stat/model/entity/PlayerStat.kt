package com.kkomi.treeisland.plugin.stat.model.entity

import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerStat")
data class PlayerStat(
        val uuid: String,
        val investmentStat: MutableMap<StatOption, Int>,
        var leftPoint: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerStat {
            return PlayerStat(
                    data["uuid"] as String,
                    (data["investmentStat"] as Map<String, Int>).mapKeys { StatOption.valueOf(it.key) } as MutableMap<StatOption, Int>,
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

