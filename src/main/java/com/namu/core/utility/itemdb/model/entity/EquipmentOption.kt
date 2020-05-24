package com.namu.core.utility.itemdb.model.entity

import com.namu.core.utility.itemdb.model.EquipmentType
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("EquipmentOption")
data class EquipmentOption(
        var levelLimit: Int,
        var equipType: EquipmentType,
        var options: List<StatOption>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipmentOption {
            return EquipmentOption(
                    data["levelLimit"] as Int,
                    EquipmentType.valueOf((data["equipType"] as String)),
                    data["options"] as List<StatOption>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "levelLimit" to levelLimit,
                "equipType" to equipType.toString(),
                "options" to options
        )
    }
}