package com.namu.core.life.maker.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("MakerCategory")
data class MakerCategory(
        var duringMessage: String,
        var name: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): MakerCategory {
            return MakerCategory(
                    data["duringMessage"] as String,
                    data["name"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "duringMessage" to duringMessage,
                "name" to name
        )
    }
}