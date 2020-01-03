package com.kkomi.treeisland.plugin.guild.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("Guild")
data class Guild(
        val name: String,
        val level: Int,
        val members: MutableMap<String, GuildMemberState>
) : ConfigurationSerializable {

    fun getTotalContribution(): Int {
        return members.values.map { it.contribution }.sum()
    }

    fun kickPlayer(uuid: String) {
        members.remove(uuid)
    }

    fun changeGrade(uuid: String, grade: GuildGrade) {
        members[uuid]!!.grade = grade
    }

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Guild {
            return Guild(
                    data["name"] as String,
                    data["level"] as Int,
                    data["members"] as MutableMap<String, GuildMemberState>
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