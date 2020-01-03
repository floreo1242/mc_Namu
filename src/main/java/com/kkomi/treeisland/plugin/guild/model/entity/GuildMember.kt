package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildMember")
data class GuildMember(
        val uuid: String,
        val grade: GuildGrade
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): GuildMember {
            return GuildMember(
                    data["uuid"] as String,
                    GuildGrade.valueOf(data["grade"] as String)
            )
        }
    }

    override fun serialize(): Map<String, String> {
        return mapOf(
                "uuid" to uuid,
                "grade" to grade.toString()
        )
    }
}
