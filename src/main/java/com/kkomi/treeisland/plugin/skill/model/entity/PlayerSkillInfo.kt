package com.kkomi.treeisland.plugin.skill.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("PlayerSkillInfo")
data class PlayerSkillInfo(
        val uuid: String,
        var isSmartSlot: Boolean,
        val learnSkills: MutableList<String>
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): PlayerSkillInfo {
            return PlayerSkillInfo(
                    data["uuid"] as String,
                    data["isSmartSlot"] as Boolean,
                    data["learnSkills"] as MutableList<String>
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "uuid" to uuid,
                "isSmartSlot" to isSmartSlot,
                "learnSkills" to learnSkills
        )
    }
}