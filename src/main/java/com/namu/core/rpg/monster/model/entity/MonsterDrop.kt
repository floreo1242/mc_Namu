package com.namu.core.rpg.monster.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("MonsterDrop")
data class MonsterDrop(
        val name: String,
        val dropItem : List<DropItem>,
        val dropExp: Int,
        val dropMoney: Int
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): MonsterDrop {
            return MonsterDrop(
                    data["name"] as String,
                    data["dropItems"] as List<DropItem>,
                    data["dropExp"] as Int,
                    data["dropMoney"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "dropItems" to dropItem,
                "dropExp" to dropExp,
                "dropMoney" to dropMoney
        )
    }
}