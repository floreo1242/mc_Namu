package com.kkomi.treeisland.plugin.shop.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("SellShop")
data class SellShop(
        val stuffList: MutableList<SellStuff>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): SellShop {
            return SellShop(
                    data["stuffList"] as MutableList<SellStuff>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "stuffList" to stuffList
        )
    }

    fun createStuff(itemStack: ItemStack, price: Int) {
        stuffList.add(SellStuff(itemStack, price))
    }

    fun removeStuff(sellStuff: SellStuff) {
        stuffList.remove(sellStuff)
    }

}