package com.namu.core.rpg.quest.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("QuestReward")
data class QuestReward(
        val items: List<QuestRewardItem>,
        val command: String,
        val exp: Int,
        val money: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): QuestReward {
            return QuestReward(
                    (data["items"] ?: listOf<QuestRewardItem>()) as List<QuestRewardItem>,
                    (data["command"] ?: "") as String,
                    (data["exp"] ?: 0) as Int,
                    (data["money"] ?: 0) as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "items" to items,
                "command" to command,
                "exp" to exp,
                "money" to money
        )
    }

}
