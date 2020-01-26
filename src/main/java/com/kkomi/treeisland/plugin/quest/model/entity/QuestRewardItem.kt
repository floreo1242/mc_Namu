package com.kkomi.treeisland.plugin.quest.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("QuestRewardItem")
data class QuestRewardItem(
        val type: QuestRewardItemType,
        val code: String,
        val amount: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): QuestRewardItem {
            return QuestRewardItem(
                    QuestRewardItemType.valueOf(data["type"] as String),
                    data["code"] as String,
                    data["amount"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "type" to type.toString(),
                "code" to code,
                "amount" to amount
        )
    }

}
