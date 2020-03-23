package com.namu.core.life.maker.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("RecipeMaterial")
data class RecipeMaterial(
    var amount: Int,
    var itemCode: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): RecipeMaterial {
            return RecipeMaterial(
                    data["amount"] as Int,
                    data["itemCode"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "amount" to amount,
                "itemCode" to itemCode
        )
    }
}