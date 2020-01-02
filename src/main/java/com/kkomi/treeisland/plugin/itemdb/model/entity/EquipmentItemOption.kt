package com.kkomi.treeisland.plugin.itemdb.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("EquipmentItemOption")
data class EquipmentItemOption(
        private val statOption: StatOption,
        private val value: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipmentItemOption {
            return EquipmentItemOption(
                    StatOption.valueOf(data["statOption"] as String),
                    data["value"] as Int
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "statOption" to statOption.toString(),
                "value" to value
        )
    }

    fun toLoreStr(): String {
        return "${statOption.strName} +$value${if (statOption.isPer) "%" else ""}"
    }

}
