package com.kkomi.treeisland.plugin.role.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("Role")
data class Role(
        var name: String,
        var defaultAttackSpellName: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): Role {
            return Role(
                    data["name"] as String,
                    data["defaultAttackSpellName"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "defaultAttackSpellName" to defaultAttackSpellName
        )
    }
}