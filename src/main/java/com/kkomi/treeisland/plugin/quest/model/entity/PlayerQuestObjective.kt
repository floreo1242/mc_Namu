package com.kkomi.treeisland.plugin.quest.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerQuestObjective")
data class PlayerQuestObjective(
        val action: QuestAction,
        var amount: Int,
        var targetAmount: Int,
        val target: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerQuestObjective {
            return PlayerQuestObjective(
                    QuestAction.valueOf(data["action"] as String),
                    data["amount"] as Int,
                    data["targetAmount"] as Int,
                    data["target"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "action" to action.name,
                "amount" to amount,
                "targetAmount" to targetAmount,
                "target" to target
        )
    }

    fun isComplete() : Boolean {
        return amount == targetAmount
    }

}
