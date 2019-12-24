package com.kkomi.treeisland.plugin.shop.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("KeywordShop")
data class KeywordShop(
        val stuffList: MutableList<KeywordStuff>
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): KeywordShop {
            return KeywordShop(
                    data["stuffList"] as MutableList<KeywordStuff>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "stuffList" to stuffList
        )
    }

    fun addStuff(keyword: String, price: Int) {
        stuffList.add(KeywordStuff(keyword, price))
    }

    fun removeStuff(keywordStuff: KeywordStuff) {
        stuffList.remove(keywordStuff)
    }

}