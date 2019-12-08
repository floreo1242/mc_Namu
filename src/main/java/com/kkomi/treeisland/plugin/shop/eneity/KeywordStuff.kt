package com.kkomi.treeisland.plugin.shop.eneity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("KeywordStuff")
data class KeywordStuff(
        val keyword : String,
        val price: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): KeywordStuff {
            return KeywordStuff(data["keyword"] as String, data["price"] as Int)
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "keyword" to keyword,
                "price" to price
        )
    }
}
