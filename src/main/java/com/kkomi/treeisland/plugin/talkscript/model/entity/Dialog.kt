package com.kkomi.treeisland.plugin.talkscript.model.entity

import com.kkomi.treeisland.library.extension.createItemStack
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("Dialog")
data class Dialog(
        val talker: String,
        val message: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Dialog {
            return Dialog(
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
                talker,
                message.split("\\n")
        )
    }
}