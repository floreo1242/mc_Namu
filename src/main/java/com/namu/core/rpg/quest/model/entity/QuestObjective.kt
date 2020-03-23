package com.namu.core.rpg.quest.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("QuestObjective")
data class QuestObjective(
        val action: QuestAction,
        var amount: Int,
        val target: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): QuestObjective {
            return QuestObjective(
                    QuestAction.valueOf(data["action"] as String),
                    data["amount"] as Int,
                    data["target"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "action" to action.name,
                "amount" to amount,
                "target" to target
        )
    }

}
