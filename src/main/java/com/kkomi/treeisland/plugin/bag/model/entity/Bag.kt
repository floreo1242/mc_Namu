package com.kkomi.treeisland.plugin.bag.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("Bag")
data class Bag(
        var itemList: List<ItemStack>,
        var level: Int,
        var uuid: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Bag {
            return Bag(
                    data["itemList"] as List<ItemStack>,
                    data["level"] as Int,
                    data["uuid"] as String
            )
        }
    }

    inner class ItemMeta(
            val bagUUID: String
    )

    fun toItemStack(): ItemStack {
        return createItemStack(
                Material.CHEST,
                "&6${level * 9}&f칸 가방",
                listOf(
                        "&f아이템을 ${level * 9}개 넣을 수 있는 가방이다."
                )
        ).run {
            addNBTTagCompound(ItemMeta(uuid))
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "itemList" to itemList,
                "level" to level,
                "uuid" to uuid
        )
    }
}