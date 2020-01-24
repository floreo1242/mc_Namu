package com.kkomi.treeisland.plugin.itemdb.model.entity

import com.kkomi.treeisland.library.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@SerializableAs("EquipmentItem")
data class EquipmentItem(
        var code: String,
        val name: String,
        val description: String,
        var equipmentType: EquipmentType,
        val material: Material,
        val durability: Int,
        val levelLimit: Int,
        val job: String,
        val options: List<EquipmentItemOption>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): EquipmentItem {
            return EquipmentItem(
                    data["code"] as String,
                    data["name"] as String,
                    data["description"] as String,
                    EquipmentType.valueOf(data["equipmentType"] as String),
                    Material.valueOf(data["material"] as String),
                    data["durability"] as Int,
                    data["levelLimit"] as Int,
                    data["job"] as String,
                    data["options"] as List<EquipmentItemOption>
            )
        }
    }

    fun toItemStack(): ItemStack {
        val itemStack = createItemStack(
                material,
                name,
                listOf(
                        "",
//                        "&f[ 장비아이템 - ${equipmentType.strName}${
//                        if (equipmentSubType.isEmpty()) {
//                            ""
//                        } else {
//                            " - $equipmentSubType"
//                        }
//                        } ]",
//                        "",
                        "&f착용직업 : $job",
                        "&f레벨제한 : $levelLimit",
                        "",
                        *options.map { "&f${it.toLoreStr()}" }.toTypedArray(),
                        "",
                        *description.split("|").toTypedArray()
                ),
                durability = durability.toShort()
        )


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
                "description" to description,
                "equipmentType" to equipmentType.toString(),
                "material" to material.toString(),
                "durability" to durability,
                "levelLimit" to levelLimit,
                "job" to job,
                "options" to options
        )
    }

}