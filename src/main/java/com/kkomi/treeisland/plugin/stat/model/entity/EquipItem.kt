package com.kkomi.treeisland.plugin.stat.model.entity

import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("EquipItemList")
data class EquipItem(
        var helmet: ItemStack = ItemStack(Material.AIR),
        var plate: ItemStack = ItemStack(Material.AIR),
        var legging: ItemStack = ItemStack(Material.AIR),
        var boots: ItemStack = ItemStack(Material.AIR),
        var glove: ItemStack = ItemStack(Material.AIR),
        var weapon: ItemStack = ItemStack(Material.AIR),
        var ring: ItemStack = ItemStack(Material.AIR),
        var glasses: ItemStack = ItemStack(Material.AIR),
        var earring: ItemStack = ItemStack(Material.AIR)
) : ConfigurationSerializable {

    fun toItemStackList(): List<ItemStack> {
        return listOfNotNull(
                helmet, plate, legging, boots, glove, weapon, ring, glasses, earring
        ).filter { it.type != Material.AIR }
    }

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipItem {
            return EquipItem(
                    data["helmet"] as ItemStack,
                    data["plate"] as ItemStack,
                    data["legging"] as ItemStack,
                    data["boots"] as ItemStack,
                    data["glove"] as ItemStack,
                    data["weapon"] as ItemStack,
                    data["ring"] as ItemStack,
                    data["glasses"] as ItemStack,
                    data["earring"] as ItemStack
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "helmet" to helmet,
                "plate" to plate,
                "legging" to legging,
                "boots" to boots,
                "glove" to glove,
                "weapon" to weapon,
                "ring" to ring,
                "glasses" to glasses,
                "earring" to earring
        )
    }
}