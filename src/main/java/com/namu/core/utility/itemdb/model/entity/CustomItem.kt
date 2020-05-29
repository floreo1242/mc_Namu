package com.namu.core.utility.itemdb.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.namu.core.utility.itemdb.util.refreshEquipmentItemLore
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@SerializableAs("CustomItem")
data class CustomItem(
        var code: String,
        var consumptionOption: ConsumptionOption?,
        var description: List<String>,
        var equipmentOption: EquipmentOption?,
        var material: Material,
        var name: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): CustomItem {
            return CustomItem(
                    data["code"] as String,
                    data["consumptionOption"] as ConsumptionOption?,
                    data["description"] as List<String>,
                    data["equipmentOption"] as EquipmentOption?,
                    Material.valueOf(data["material"] as String),
                    data["name"] as String
            )
        }
    }

    fun toItemStack(): ItemStack {
        return createItemStack(
                material,
                name,
                description
        ).run {
            addNBTTagCompound(this@CustomItem)
        }.apply {
            refreshEquipmentItemLore()
            itemMeta = itemMeta?.apply {
                isUnbreakable = true
                addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                addItemFlags(ItemFlag.HIDE_PLACED_ON)
                addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
                addItemFlags(ItemFlag.HIDE_DESTROYS)
            }
        }
    }

    override fun serialize(): Map<String, Any?> {
        return mapOf(
                "code" to code,
                "consumptionOption" to consumptionOption,
                "description" to description,
                "equipmentOption" to equipmentOption,
                "material" to material.toString(),
                "name" to name
        )
    }
}