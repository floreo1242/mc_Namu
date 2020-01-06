package com.kkomi.treeisland.plugin.skill.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerSkill")
class PlayerSkill(

) : ConfigurationSerializable {
    companion object {
        fun deserialize(data: Map<String, Any>): PlayerSkill {
            return PlayerSkill()
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf()
    }

}