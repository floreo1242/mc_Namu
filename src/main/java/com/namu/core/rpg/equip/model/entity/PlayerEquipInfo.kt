package com.namu.core.rpg.equip.model.entity

import com.namu.core.utility.itemdb.model.EquipmentType
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("PlayerEquipInfo")
data class PlayerEquipInfo(
        var items: Map<EquipmentType, ItemStack>,
        var uuid: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerEquipInfo {
            return PlayerEquipInfo(
                    (data["items"] as Map<String, ItemStack>).mapKeys { EquipmentType.valueOf(it.key) },
                    data["uuid"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "items" to items.map { it.key.toString() to it.value }.toMap(),
                "uuid" to uuid
        )
    }
}