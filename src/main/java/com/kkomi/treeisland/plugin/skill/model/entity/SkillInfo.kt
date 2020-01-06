package com.kkomi.treeisland.plugin.skill.model.entity

import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs

@SerializableAs("SkillInfo")
data class SkillInfo(
        val name: String,
        val displayName: String,
        val description: String,
        val magicSpellName: String,
        val coefficient: Double
) : ConfigurationSerializable {

    companion object {
        fun deserialize(data: Map<String, Any>): SkillInfo {
            return SkillInfo(
                    data["name"] as String,
                    data["displayName"] as String,
                    data["description"] as String,
                    data["magicSpellName"] as String,
                    data["coefficient"] as Double
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "displayName" to displayName,
                "description" to description,
                "magicSpellName" to magicSpellName,
                "coefficient" to coefficient
        )
    }

}