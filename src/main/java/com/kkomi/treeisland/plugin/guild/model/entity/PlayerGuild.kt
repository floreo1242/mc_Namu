package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerGuild")
data class PlayerGuild(
        val uuid: String,
        var guildName: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerGuild {
            return PlayerGuild(
                    data["uuid"] as String,
                    data["guildName"] as String
            )
        }
    }

    override fun serialize(): Map<String, String> {
        return mapOf(
                "uuid" to uuid,
                "guildName" to guildName
        )
    }
}