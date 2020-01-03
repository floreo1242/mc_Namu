package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("Guild")
data class Guild(
        val name: String,
        val level: Int,
        val members: MutableList<GuildMember>
) : ConfigurationSerializable {
    companion object {
        fun deserialize(data: Map<String, Any>): Guild {
            return Guild(
                    data["name"] as String,
                    data["level"] as Int,
                    data["members"] as MutableList<GuildMember>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "level" to level,
                "members" to members
        )
    }
}