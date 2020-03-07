package com.kkomi.treeisland.plugin.itemdb.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("EquipmentItemOption")
data class EquipmentItemOption(
        val statOption: StatOption,
        val value: Int
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

    fun toLoreStr(plusValue: Int = 0): String {
        return if (plusValue == 0) {
            "&f${statOption.strName} : +${value}${if (statOption.isPer) "%" else ""}"
        } else {
            "&f${statOption.strName} : +&b${value + plusValue}&f ($value + $plusValue)${if (statOption.isPer) "%" else ""}"
        }
    }

}
