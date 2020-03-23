package com.namu.core.rpg.skill.model.entity

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.nms.addNBTTagCompound
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@SerializableAs("SkillInfo")
data class SkillInfo(
        val name: String,
        val displayName: String,
        val description: String,
        val levelLimit: Int,
        val roleLimit: String,
        val magicSpellName: String
) : ConfigurationSerializable {
    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): SkillInfo {
            return SkillInfo(
                    data["name"] as String,
                    data["displayName"] as String,
                    data["description"] as String,
                    data["levelLimit"] as Int,
                    data["roleLimit"] as String,
                    data["magicSpellName"] as String
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "name" to name,
                "displayName" to displayName,
                "description" to description,
                "levelLimit" to levelLimit,
                "roleLimit" to roleLimit,
                "magicSpellName" to magicSpellName
        )
    }

    fun toItemStack(isSkillBook: Boolean = false): ItemStack {
        return createItemStack(
                Material.ENCHANTED_BOOK,
                displayName + if (isSkillBook) " 스킬북" else "",
                mutableListOf(
                        "",
                        "&f레벨제한 : &6$levelLimit",
                        "&f직업제한 : &6$roleLimit",
                        "",
                        *description.split("|").toTypedArray()
                ).apply {
                    if (isSkillBook) {
                        add("")
                        add("&7우클릭시 스킬을 획득합니다.")
                    }
                }
        ).addNBTTagCompound(SkillBookItemMeta(this@SkillInfo, isSkillBook))
    }

    fun cast(player: Player) {
//        MagicSpells.getSpellByInGameName(magicSpellName).cast(player)
    }
}