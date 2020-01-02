package com.kkomi.treeisland.plugin.stat.inventory

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class EquipItemInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "플레이어 정보"

        const val HEAD = 10
        const val WEAPON = 12
        const val RING = 14
        const val EARRING = 15
        const val GLASSES = 16
        const val HELMET = 30
        const val PLATE = 31
        const val LEGGINGS = 32
        const val BOOTS = 33
        const val GLOVE = 34

        val equipItemSlotList = listOf(WEAPON, RING, EARRING, GLASSES, HELMET, PLATE, LEGGINGS, BOOTS, GLOVE)
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 45, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        for (i in 0 until 45) {
            inventory.setItem(i, createItemStack(Material.STAINED_GLASS_PANE, "", listOf(), durability = 15))
        }

        val statInfo = player.getPlayerInfo().statInfo

        inventory.setItem(HEAD, createItemStack(
                Material.SKULL_ITEM,
                "${player.name}님의 장비 정보",
                statInfo.finalStat.map {
                    "&f${it.key.strName} : ${it.value}${if (it.key.isPer) "%" else ""}"
                },
                durability = 3))

        inventory.setItem(WEAPON, statInfo.equipItem.weapon)
        inventory.setItem(RING, statInfo.equipItem.ring)
        inventory.setItem(EARRING, statInfo.equipItem.earring)
        inventory.setItem(GLASSES, statInfo.equipItem.glasses)
        inventory.setItem(HELMET, statInfo.equipItem.helmet)
        inventory.setItem(PLATE, statInfo.equipItem.plate)
        inventory.setItem(LEGGINGS, statInfo.equipItem.legging)
        inventory.setItem(BOOTS, statInfo.equipItem.boots)
        inventory.setItem(GLOVE, statInfo.equipItem.glove)

    }

}