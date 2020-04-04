package com.namu.core.rpg.monster.model.entity

import com.namu.core.utility.itemdb.model.CustomItemRepository
import com.namu.core.rpg.skill.model.SkillInfoRepository
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("DropItem")
data class DropItem(
        val type: DropItemType,
        val code: String,
        val amount: Int,
        val chance: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): DropItem {
            return DropItem(
                    DropItemType.valueOf(data["type"] as String),
                    data["code"] as String,
                    data["amount"] as Int,
                    data["chance"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "type" to type.toString(),
                "code" to code,
                "amount" to amount,
                "chance" to chance
        )
    }

    fun toItemStack(): ItemStack {
        val a = amount
        return when (type) {
            DropItemType.CUSTOM_ITEM -> CustomItemRepository.getCustomItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            DropItemType.SKILL_BOOK -> SkillInfoRepository.getSkillInfo(code)?.toItemStack(true)
                    ?: ItemStack(Material.AIR)
        }.apply {
            amount = a
        }
    }

}
