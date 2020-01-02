package com.kkomi.treeisland.plugin.itemdb.model.entity.dummy

import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItemOption
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentType
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.Material
import org.bukkit.entity.Player

object EquipmentDataDummy {

    fun get(player: Player) {
        val test_sword = EquipmentItem(
                "dark_moon_two_hand_sword",
                "&e다크문 양손검",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.WEAPON,
                "양손검",
                Material.WOOD_SWORD,
                0,
                200,
                "전사",
                StatOption.values().map { EquipmentItemOption(it, 30) }
        )

        val test_knife = EquipmentItem(
                "dark_moon_two_hand_knife",
                "&e다크문 단도",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.WEAPON,
                "단도",
                Material.WOOD_SWORD,
                0,
                200,
                "도적",
                StatOption.values().map { EquipmentItemOption(it, 100) }
        )

        EquipmentItemRepository.createItem(test_sword)
        EquipmentItemRepository.createItem(test_knife)
        player.inventory.addItem(test_sword.toItemStack())
        player.inventory.addItem(test_knife.toItemStack())
    }

}