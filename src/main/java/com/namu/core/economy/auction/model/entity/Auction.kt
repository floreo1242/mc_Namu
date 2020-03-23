package com.namu.core.economy.auction.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("Auction")
data class Auction(
        var auctionStuffList: MutableList<AuctionStuff>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Auction {
            return Auction(
                    data["auctionStuffList"] as MutableList<AuctionStuff>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "auctionStuffList" to auctionStuffList
        )
    }
}