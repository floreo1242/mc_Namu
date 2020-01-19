package com.kkomi.treeisland.plugin.skill.model.entity

import com.nisovin.magicspells.MagicSpells
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player

@SerializableAs("SkillInfo")
data class SkillInfo(
        val name: String,
        val displayName: String,
        val description: String,
        val magicSpellName: String
) : ConfigurationSerializable {
    companion object {
        fun deserialize(data: Map<String, Any>): SkillInfo {
            return SkillInfo(
                    data["name"] as String,
                    data["displayName"] as String,
                    data["description"] as String,
                    data["magicSpellName"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "displayName" to displayName,
                "description" to description,
                "magicSpellName" to magicSpellName
        )
    }

    fun cast(player: Player) {
        MagicSpells.getSpellByInternalName(magicSpellName).cast(player)
    }
}