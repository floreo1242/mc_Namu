package com.kkomi.treeisland.plugin.post.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("PlayerPostBox")
data class PlayerPostBox(
        var postItems: MutableList<ItemStack>,
        var uuid: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerPostBox {
            return PlayerPostBox(
                    data["postItems"] as MutableList<ItemStack>,
                    data["uuid"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "postItems" to postItems,
                "uuid" to uuid
        )
    }
}