package com.kkomi.treeisland.plugin.craft.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("CraftRecipe")
data class CraftRecipe(
        val name: String,
        val materialList: List<String>,
        val result: String,
        val isPublic: Boolean
) : ConfigurationSerializable {

    companion object {
        fun deserialize(data: Map<String, Any>): CraftRecipe {
            return CraftRecipe(
                    data["name"] as String,
                    data["materialList"] as List<String>,
                    data["result"] as String,
                    data["isPublic"] as Boolean
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "materialList" to materialList,
                "result" to result,
                "isPublic" to isPublic
        )
    }

}