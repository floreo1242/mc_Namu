package com.namu.core.rpg.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("GuildMemberState")
data class GuildMemberState(
        var grade: GuildGrade,
        var contribution: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): GuildMemberState {
            return GuildMemberState(
                    GuildGrade.valueOf(data["grade"] as String),
                    data["contribution"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "grade" to grade.toString(),
                "contribution" to contribution
        )
    }
}
