package com.kkomi.treeisland.plugin.itemdb.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@SerializableAs("EquipmentItem")
data class EquipmentItem(
        var code: String,
        val name: String,
        var equipmentType: EquipmentType,
        val material: Material,
        val durability: Int,
        val levelLimit: Int,
        val job: String,
        val baseOptions: List<EquipmentItemOption>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipmentItem {
            return EquipmentItem(
                    data["code"] as String,
                    data["name"] as String,
                    EquipmentType.valueOf(data["equipmentType"] as String),
                    Material.valueOf(data["material"] as String),
                    data["durability"] as Int,
                    data["levelLimit"] as Int,
                    data["job"] as String,
                    (data["baseOptions"] ?: data["options"]) as List<EquipmentItemOption>
            )
        }
    }

    fun toItemStack(): ItemStack {
        val itemStack = createItemStack(
                material,
                name,
                listOf(
                        "",
                        "&f착용직업 : $job",
                        "&f레벨제한 : $levelLimit",
                        "",
                        *baseOptions.map { "&f${it.toLoreStr()}" }.toTypedArray()
                ),
                durability = durability.toShort()
        ).run {
            addNBTTagCompound(this@EquipmentItem)
        }

        val itemMeta = itemStack.itemMeta
        itemMeta.isUnbreakable = true
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON)

        itemStack.itemMeta = itemMeta

        return itemStack
    }


    override fun serialize(): Map<String, Any> {
        return mapOf(
                "code" to code,
                "name" to name,
                "equipmentType" to equipmentType.toString(),
                "material" to material.toString(),
                "durability" to durability,
                "levelLimit" to levelLimit,
                "job" to job,
                "baseOptions" to baseOptions
        )
    }

}