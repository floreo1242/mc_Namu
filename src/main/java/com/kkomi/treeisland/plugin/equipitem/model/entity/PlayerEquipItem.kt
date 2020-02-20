package com.kkomi.treeisland.plugin.equipitem.model.entity

import com.kkomi.devlibrary.extension.*
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack
import java.util.regex.Pattern

@SerializableAs("EquipItemList")
data class PlayerEquipItem(
        var uuid: String,
        var helmet: ItemStack = ItemStack(Material.AIR),
        var plate: ItemStack = ItemStack(Material.AIR),
        var legging: ItemStack = ItemStack(Material.AIR),
        var boots: ItemStack = ItemStack(Material.AIR),
        var weapon: ItemStack = ItemStack(Material.IRON_BARDING)
) : ConfigurationSerializable {

    private fun toItemStackList(): List<ItemStack> {
        return listOfNotNull(
                helmet, plate, legging, boots, weapon
        ).filter { it.type != Material.AIR }
    }

    fun getEquipmentItemStat(): Map<StatOption, Int> {
        val equipmentStat = mutableMapOf<StatOption, Int>()
        toItemStackList()
                .flatMap {
                    it.getLore() ?: listOf()
                }
                .filter {
                    it.startsWith("§f+") || it.startsWith("§f-")
                }
                .forEach { data ->
                    val temp = data.removeChatColorCode().split(" ")
                    val optionValue = temp[0].run { substring(1 until if (endsWith("%")) length - 1 else length) }.toInt()
                    val optionName = StatOption.values().find { it.strName == temp.join(1, temp.size - 1) }!!
                    equipmentStat[optionName] = (equipmentStat[optionName] ?: 0) + optionValue
                }
        return equipmentStat
    }

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerEquipItem {
            return PlayerEquipItem(
                    data["uuid"] as String,
                    data["helmet"] as ItemStack,
                    data["plate"] as ItemStack,
                    data["legging"] as ItemStack,
                    data["boots"] as ItemStack,
                    data["weapon"] as ItemStack
            )
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        return mutableMapOf(
                "uuid" to uuid,
                "helmet" to helmet,
                "plate" to plate,
                "legging" to legging,
                "boots" to boots,
                "weapon" to weapon
        )
    }
}