package com.namu.core.rpg.mana.model.mana.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("ManaConfig")
data class ManaConfig(
        var levelByMana: Map<Int, Int>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): ManaConfig {
            return ManaConfig(
                    data["levelByMana"] as Map<Int, Int>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelByMana" to levelByMana
        )
    }
}