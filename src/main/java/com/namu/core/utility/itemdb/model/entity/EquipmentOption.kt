package com.namu.core.utility.itemdb.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("EquipmentOption")
data class EquipmentOption(
        var levelLimit: Int,
        var options: List<StatOption>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipmentOption {
            return EquipmentOption(
                    data["levelLimit"] as Int,
                    data["options"] as List<StatOption>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelLimit" to levelLimit,
                "options" to options
        )
    }
}