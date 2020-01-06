package com.kkomi.treeisland.plugin.itemdb.model.entity

import com.kkomi.treeisland.library.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionType

@SerializableAs("ConsumptionItem")
data class ConsumptionItem(
        val code: String,
        val name: String,
        val description: String,
        val material: Material,
        val durability: Int,
        val levelLimit: Int,
        val duration: Int,
        val value: Int,
        val cooldown: Int,
        val type: ConsumptionItemType
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): ConsumptionItem {
            return ConsumptionItem(
                    data["code"] as String,
                    data["name"] as String,
                    data["description"] as String,
                    Material.valueOf(data["material"] as String),
                    data["durability"] as Int,
                    data["levelLimit"] as Int,
                    data["duration"] as Int,
                    data["value"] as Int,
                    data["cooldown"] as Int,
                    ConsumptionItemType.valueOf(data["type"] as String)
            )
        }
    }

    fun toItemStack(): ItemStack {
        return createItemStack(
                material,
                name,
                listOf(
                        "",
                        "&f[ 소비아이템 ]",
                        "",
                        "&9레벨 : $levelLimit",
                        "",
                        *description.split("|").toTypedArray()
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
                "durability" to durability,
                "levelLimit" to levelLimit,
                "duration" to duration,
                "value" to value,
                "cooldown" to cooldown,
                "type" to type.toString()
        )
    }
}