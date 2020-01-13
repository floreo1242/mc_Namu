package com.kkomi.treeisland.plugin.quest.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("QuestReward")
data class QuestReward(
        val items : List<String>,
        val command : String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data : Map<String,Any>): QuestReward {
            return QuestReward(
                    data["items"] as List<String>,
                    data["command"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "items" to items,
                "command" to command
        )
    }

}
