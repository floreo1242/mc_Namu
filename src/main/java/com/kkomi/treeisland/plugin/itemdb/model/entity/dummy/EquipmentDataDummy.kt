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
                "test_weapon",
                "&eTEST WEAPON",
                "&fThis is test weapon",
                EquipmentType.WEAPON,
                Material.WOOD_SWORD,
                0,
                200,
                "Warrior",
                StatOption.values().map { EquipmentItemOption(it, 10) }
        )

        val test_helmet = EquipmentItem(
                "test_helmet",
                "&eTEST HELMET",
                "&fThis is test helmet",
                EquipmentType.HELMET,
                Material.LEATHER_HELMET,
                0,
                200,
                "Warrior",
                StatOption.values().map { EquipmentItemOption(it, 10) }
        )

        val test_chest_plate = EquipmentItem(
                "test_chest_plate",
                "&eTEST CHEST_PLATE",
                "&fThis is test chest plate",
                EquipmentType.PLATE,
                Material.LEATHER_CHESTPLATE,
                0,
                200,
                "Warrior",
                StatOption.values().map { EquipmentItemOption(it, 10) }
        )

        val test_leggings = EquipmentItem(
                "test_leggings",
                "&eTEST LEGGINGS",
                "&fThis is test leggings",
                EquipmentType.LEGGINGS,
                Material.LEATHER_LEGGINGS,
                0,
                200,
                "Warrior",
                StatOption.values().map { EquipmentItemOption(it, 10) }
        )

        val test_boots = EquipmentItem(
                "test_boots",
                "&eTEST BOOTS",
                "&fThis is test boots",
                EquipmentType.BOOTS,
                Material.LEATHER_BOOTS,
                0,
                200,
                "Warrior",
                StatOption.values().map { EquipmentItemOption(it, 10) }
        )

        EquipmentItemRepository.createItem(test_sword)
        EquipmentItemRepository.createItem(test_helmet)
        EquipmentItemRepository.createItem(test_chest_plate)
        EquipmentItemRepository.createItem(test_leggings)
        EquipmentItemRepository.createItem(test_boots)
        player.inventory.addItem(test_sword.toItemStack())
        player.inventory.addItem(test_helmet.toItemStack())
        player.inventory.addItem(test_chest_plate.toItemStack())
        player.inventory.addItem(test_leggings.toItemStack())
        player.inventory.addItem(test_boots.toItemStack())
    }

}