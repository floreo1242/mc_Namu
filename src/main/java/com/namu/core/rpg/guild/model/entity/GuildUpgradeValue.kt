package com.namu.core.rpg.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildUpgradeValue")
class GuildUpgradeValue(
        val guildPoint: Int,
        val money: Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): GuildUpgradeValue {
            return GuildUpgradeValue(
                    data["guildPoint"] as Int,
                    data["money"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "guildPoint" to guildPoint,
                "money" to money
        )
    }
}