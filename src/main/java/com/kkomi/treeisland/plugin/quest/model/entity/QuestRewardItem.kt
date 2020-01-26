package com.kkomi.treeisland.plugin.quest.model.entity

import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.configuration.serialization.SerializableAs
import org.bukkit.inventory.ItemStack

@SerializableAs("QuestRewardItem")
data class QuestRewardItem(
        val type: QuestRewardItemType,
        val code: String,
        val amount: Int
) : ConfigurationSerializable {

    companion object {
        @JvmStatic
        fun deserialize(data: Map<String, Any>): QuestRewardItem {
            return QuestRewardItem(
                    QuestRewardItemType.valueOf(data["type"] as String),
                    data["code"] as String,
                    data["amount"] as Int
            )
        }
    }

    override fun serialize(): Map<String, Any> {
        return mapOf(
                "type" to type.toString(),
                "code" to code,
                "amount" to amount
        )
    }

    fun toItemStack(): ItemStack {
        val a = amount
        return when (type) {
            QuestRewardItemType.EQUIPMENT_ITEM -> EquipmentItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            QuestRewardItemType.OTHER_ITEM -> OtherItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            QuestRewardItemType.CONSUMPTION_ITEM -> ConsumptionItemRepository.getItem(code)?.toItemStack()
                    ?: ItemStack(Material.AIR)
            QuestRewardItemType.SKILL_BOOK -> SkillInfoRepository.getSkillInfo(code)?.toItemStack(true)
                    ?: ItemStack(Material.AIR)
        }.apply {
            amount = a
        }
    }

}
