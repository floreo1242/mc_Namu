package com.namu.core.rpg.level.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerLevel")
data class PlayerLevel(
        val uuid: String,
        var exp: Int,
        var level: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerLevel {
            return PlayerLevel(
                    data["uuid"] as String,
                    data["exp"] as Int,
                    data["level"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "uuid" to uuid,
                "exp" to exp,
                "level" to level
        )
    }

}