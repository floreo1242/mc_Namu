package com.kkomi.treeisland.plugin.role.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerRole")
data class PlayerRole(
        var uuid: String,
        var role: Role
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerRole {
            return PlayerRole(
                    data["uuid"] as String,
                    data["role"] as Role
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "uuid" to uuid,
                "role" to role
        )
    }
}