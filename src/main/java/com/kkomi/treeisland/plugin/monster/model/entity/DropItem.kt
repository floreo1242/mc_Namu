package com.kkomi.treeisland.plugin.monster.model.entity

import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
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

    fun toItemStack() : ItemStack {
        return when (type) {
            DropItemType.EQUIPMENT_ITEM -> EquipmentItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            DropItemType.OTHER_ITEM -> OtherItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            DropItemType.CONSUMPTION_ITEM -> OtherItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            DropItemType.SKILL_BOOK -> SkillInfoRepository.getSkillInfo(code)?.toItemStack(true)
                    ?: ItemStack(Material.AIR)
        }
    }

}
