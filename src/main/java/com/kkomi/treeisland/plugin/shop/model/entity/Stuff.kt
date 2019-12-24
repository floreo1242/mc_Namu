package com.kkomi.treeisland.plugin.shop.model.entity

import com.kkomi.treeisland.library.extension.replaceChatColorCode
import com.kkomi.treeisland.library.extension.toMoneyFormat
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("Stuff")
data class Stuff(
        val itemStack: ItemStack,
        val price: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Stuff {
            return Stuff(data["itemStack"] as ItemStack, data["price"] as Int)
        }
    }

    fun toItemStack(): ItemStack {
        val itemStack = itemStack.clone()
        val itemMeta = itemStack.itemMeta

        val lore = mutableListOf<String>(*(itemMeta.lore ?: listOf<String>()).toTypedArray())
        lore.add("")
        lore.add("&f구매가격 : ${price.toMoneyFormat()}".replaceChatColorCode())

        itemMeta.lore = lore
        itemStack.itemMeta = itemMeta

        return itemStack
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "itemStack" to itemStack,
                "price" to price
        )
    }
}
