package com.kkomi.treeisland.plugin.itemdb.model.entity

import com.kkomi.treeisland.library.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
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
        return createItemStack(
                material,
                name,
                listOf(
                        "",
                        "&f[ 기타아이템 ]",
                        "",
                        *description.split("\\n").toTypedArray()
                ),
                durability = durability.toShort()
        )
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