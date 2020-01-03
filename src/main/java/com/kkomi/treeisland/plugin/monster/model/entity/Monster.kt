package com.kkomi.treeisland.plugin.monster.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("Monster")
data class Monster(
        val name: String,
        val dropExp: Int,
        val dropMoney: Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Monster {
            return Monster(
                    data["name"] as String,
                    data["dropExp"] as Int,
                    data["dropMoney"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "dropExp" to dropExp,
                "dropMoney" to dropMoney
        )
    }
}