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
        val weapon = EquipmentItem(
                "dark_moon_two_hand_sword",
                "&e다크문 양손검",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.WEAPON,
                Material.WOOD_SWORD,
                0,
                200,
                "전사",
                listOf(
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 344),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 569),
                        EquipmentItemOption(StatOption.PHYSICS_MIN_DAMAGE, 20, true),
                        EquipmentItemOption(StatOption.STRENGTH, 300),
                        EquipmentItemOption(StatOption.BASIC_STAT, 150)
                )
        )

        val helmet = EquipmentItem(
                "mity_helmet",
                "&e마이티 헬멧",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.HELMET,
                Material.DIAMOND_HELMET,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 556),
                        EquipmentItemOption(StatOption.LUCK, 103),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.PHYSICS_MAX_DAMAGE, 27, true)
                )
        )

        val plate = EquipmentItem(
                "mity_plate",
                "&e마이티 플레이트",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.PLATE,
                Material.DIAMOND_CHESTPLATE,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 556),
                        EquipmentItemOption(StatOption.LUCK, 103),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.PHYSICS_MIN_DAMAGE, 5, true),
                        EquipmentItemOption(StatOption.CRITICAL_CHANCE, 5, true)
                )
        )

        val boots = EquipmentItem(
                "mity_helmet",
                "&e마이티 부츠",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.BOOTS,
                Material.DIAMOND_BOOTS,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 556),
                        EquipmentItemOption(StatOption.LUCK, 103),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.WALK_SPEED, 42, true)
                )
        )

        val chrip = EquipmentItem(
                "mity_chrip",
                "&e마이티 클립",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.LEGGINGS,
                Material.DIAMOND_LEGGINGS,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 556),
                        EquipmentItemOption(StatOption.LUCK, 103),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.PHYSICS_MIN_DAMAGE, 27, true)
                )
        )

        val glove = EquipmentItem(
                "mity_glove",
                "&e마이티 클립",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.GLOVE,
                Material.DIAMOND_LEGGINGS,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 556),
                        EquipmentItemOption(StatOption.LUCK, 103),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.CRITICAL_DAMAGE, 38, true)
                )
        )

        val earring = EquipmentItem(
                "mity_earring",
                "&e마이티 귀걸이",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.EARRING,
                Material.NETHER_STAR,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 300),
                        EquipmentItemOption(StatOption.LUCK, 170),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.PHYSICS_MIN_DAMAGE, 7, true),
                        EquipmentItemOption(StatOption.CRITICAL_CHANCE, 4, true)
                )
        )

        val ring = EquipmentItem(
                "mity_ring",
                "&e마이티 반지",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.RING,
                Material.APPLE,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 300),
                        EquipmentItemOption(StatOption.LUCK, 170),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 5),
                        EquipmentItemOption(StatOption.MAX_DAMAGE, 5),
                        EquipmentItemOption(StatOption.PHYSICS_MIN_DAMAGE, 7, true),
                        EquipmentItemOption(StatOption.CRITICAL_CHANCE, 4, true)
                )
        )

        val glasses = EquipmentItem(
                "mity_glasses",
                "&e마이티 안경",
                "&f위그드라실의 힘이 담겨있다.",
                EquipmentType.GLASSES,
                Material.GLASS,
                0,
                200,
                "공용",
                listOf(
                        EquipmentItemOption(StatOption.STRENGTH, 300),
                        EquipmentItemOption(StatOption.LUCK, 170),
                        EquipmentItemOption(StatOption.MIN_DAMAGE, 10),
                        EquipmentItemOption(StatOption.PHYSICS_MAX_DAMAGE, 7, true)
                )
        )

        EquipmentItemRepository.createItem(weapon)
        EquipmentItemRepository.createItem(helmet)
        EquipmentItemRepository.createItem(plate)
        EquipmentItemRepository.createItem(chrip)
        EquipmentItemRepository.createItem(boots)
        EquipmentItemRepository.createItem(glove)
        EquipmentItemRepository.createItem(earring)
        EquipmentItemRepository.createItem(glasses)
        EquipmentItemRepository.createItem(ring)

        player.inventory.addItem(weapon.toItemStack())
        player.inventory.addItem(helmet.toItemStack())
        player.inventory.addItem(plate.toItemStack())
        player.inventory.addItem(chrip.toItemStack())
        player.inventory.addItem(boots.toItemStack())
        player.inventory.addItem(glove.toItemStack())
        player.inventory.addItem(earring.toItemStack())
        player.inventory.addItem(glasses.toItemStack())
        player.inventory.addItem(ring.toItemStack())
    }

}