package com.namu.core.life.maker.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerRecipe")
data class PlayerRecipe(
    var recipeList: MutableList<String>,
    var uuid: String
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerRecipe {
            return PlayerRecipe(
                    data["recipeList"] as MutableList<String>,
                    data["uuid"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "recipeList" to recipeList,
                "uuid" to uuid
        )
    }
}