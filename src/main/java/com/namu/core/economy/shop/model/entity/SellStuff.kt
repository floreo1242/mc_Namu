package com.namu.core.economy.shop.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("SellStuff")
data class SellStuff(
        val itemStack: ItemStack,
        val price: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): SellStuff {
            return SellStuff(data["itemStack"] as ItemStack, data["price"] as Int)
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "itemStack" to itemStack,
                "price" to price
        )
    }
}
