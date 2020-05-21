package com.namu.core.economy.auction.model.entity

import com.kkomi.devlibrary.extension.*
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.namu.core.economy.auction.model.entity.AuctionStuff.Meta
import org.bukkit.ChatColor
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("AuctionStuff")
data class AuctionStuff(
        var item: ItemStack,
        var ownerUuid: String,
        var price: Int,
        var uuid: String
) : ConfigurationSerializable {

    data class Meta(
            var ownerUuid: String,
            var price: Int,
            var uuid: String
    )

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): AuctionStuff {
            return AuctionStuff(
                    data["item"] as ItemStack,
                    data["ownerUuid"] as String,
                    data["price"] as Int,
                    data["uuid"] as String
            )
        }
    }

    fun toItemStack(): ItemStack {
        return item.clone().apply {
            val lastedLore = mutableListOf<String>()
            if (!getLore().isNullOrEmpty()) {
                lastedLore.addAll(getLore() ?: listOf())
            }
            lastedLore.add("")
            lastedLore.add("&6판매 금액&f : ${price.toMoneyFormat()}".replaceChatColorCode())
            setLore(lastedLore)
        }.run { addNBTTagCompound(Meta(ownerUuid, price, uuid)) }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "item" to item,
                "ownerUuid" to ownerUuid,
                "price" to price,
                "uuid" to uuid
        )
    }
}