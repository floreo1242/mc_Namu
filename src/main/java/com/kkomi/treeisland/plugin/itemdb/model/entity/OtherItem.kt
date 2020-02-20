package com.kkomi.treeisland.plugin.itemdb.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.material.Dye

@SerializableAs("OtherItem")
data class OtherItem(
        val code: String,
        val name: String,
        val description: String,
        val material: Material,
        val durability: Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): OtherItem {
            return OtherItem(
                    data["code"] as String,
                    data["name"] as String,
                    data["description"] as String,
                    Material.valueOf(data["material"] as String),
                    data["durability"] as Int
            )
        }
    }

    fun toItemStack(): ItemStack {
        val itemStack = createItemStack(
                material,
                name,
                listOf(
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
                "material" to material.toString(),
                "durability" to durability
        )
    }
}