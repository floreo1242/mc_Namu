package com.namu.core.rpg.mana.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerMana")
data class PlayerMana(
        var maxMana: Int,
        var mana: Int,
        var uuid: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerMana {
            return PlayerMana(
                    data["maxMana"] as Int,
                    data["maxMana"] as Int,
                    data["uuid"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "maxMana" to mana,
                "uuid" to uuid
        )
    }
}