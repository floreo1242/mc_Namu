package com.kkomi.treeisland.plugin.equipitem.model.entity

import com.kkomi.devlibrary.extension.*
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.treeisland.plugin.enhance.model.EnhanceItemMeta
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItemOption
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
                .mapNotNull {
                    it.getNBTTagCompound(EquipmentItem::class.java)
                }
                .map {
                    it.baseOptions
                }
                .forEach { data ->
                    data.forEach {
                        equipmentStat[it.statOption] = (equipmentStat[it.statOption] ?: 0) + it.value
                    }
                }

        toItemStackList()
                .mapNotNull {
                    it.getNBTTagCompound(EnhanceItemMeta::class.java)
                }
                .map {
                    it.scrollOptions
                }
                .forEach { data ->
                    data.forEach {
                        equipmentStat[it.first] = (equipmentStat[it.first] ?: 0) + it.second
                    }
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