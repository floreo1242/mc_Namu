package com.kkomi.treeisland.plugin.shop.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("Shop")
data class Shop(
        val name: String,
        val stuffList: MutableList<Stuff>,
        var npcName: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Shop {
            return Shop(
                    data["name"] as String,
                    data["stuffList"] as MutableList<Stuff>,
                    data["npcName"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "stuffList" to stuffList,
                "npcName" to npcName
        )
    }

    fun getLastPageNum(): Int {
        if (stuffList.isEmpty()) return 1
        var page = stuffList.size / 36
        if (stuffList.size % 36 > 0) {
            page += 1
        }
        return page
    }

    fun getStuffList(page: Int): List<Stuff> {
        return if (stuffList.isEmpty()) {
            listOf()
        } else {
            if (page == getLastPageNum()) {
                stuffList.toTypedArray().copyOfRange((page - 1) * 36, stuffList.size).toList()
            } else {
                stuffList.toTypedArray().copyOfRange((page - 1) * 36, page * 36 - 1).toList()
            }
        }
    }

    fun addStuff(itemStack: ItemStack, price: Int) {
        stuffList.add(Stuff(itemStack, price))
    }

    fun removeStuff(index: Int) {
        stuffList.removeAt(index)
    }
}