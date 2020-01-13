package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.library.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("TalkScript")
data class TalkScript(
        val talker: String,
        val message: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): TalkScript {
            return TalkScript(
                    data["talker"] as String,
                    data["message"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "talker" to talker,
                "message" to message
        )
    }

    fun toItemStack(): ItemStack {
        return createItemStack(
                Material.BOOK_AND_QUILL,
                "&f$talker",
                message.split("\\n").map { "&f$it" }
        )
    }
}