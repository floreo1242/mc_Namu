package com.namu.core.utility.itemdb.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("ConsumptionOption")
data class ConsumptionOption(
    var command: String,
    var cooldown: Int,
    var levelLimit: Int
) : ConfigurationSerializable {

    companion object {
    @JvmStatic
        fun deserialize(data: Map<String, Any>): ConsumptionOption {
            return ConsumptionOption(
                    data["command"] as String,
                    data["cooldown"] as Int,
                    data["levelLimit"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "command" to command,
                "cooldown" to cooldown,
                "levelLimit" to levelLimit
        )
    }
}