package com.namu.core.rpg.role.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerRole")
data class PlayerRole(
        var uuid: String,
        var roleName: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerRole {
            return PlayerRole(
                    data["uuid"] as String,
                    data["roleName"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "uuid" to uuid,
                "roleName" to roleName
        )
    }
}