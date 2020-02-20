package com.kkomi.treeisland.plugin.equipitem.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class EquipItemInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "플레이어 정보"

        const val HEAD = 8
        const val WEAPON = 10
        const val HELMET = 11
        const val PLATE = 12
        const val LEGGINGS = 13
        const val BOOTS = 14

        val equipItemSlotList = listOf(WEAPON, HELMET, PLATE, LEGGINGS, BOOTS)
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${player.name}")

    override fun setBasicFrame() {

        val statInfo = player.getPlayerInfo().statInfo
        val equipItemInfo = player.getPlayerInfo().equipmentInfo

        inventory.setItem(HEAD, createItemStack(
                Material.SKULL_ITEM,
                "${player.name}님의 장비 정보",
                statInfo.finalStat.map {
                    "&f${it.key.strName} : ${it.value}${if (it.key.isPer) "%" else ""}"
                },
                durability = 3))

        inventory.setItem(WEAPON, equipItemInfo.weapon)
        inventory.setItem(HELMET, equipItemInfo.helmet)
        inventory.setItem(PLATE, equipItemInfo.plate)
        inventory.setItem(LEGGINGS, equipItemInfo.legging)
        inventory.setItem(BOOTS, equipItemInfo.boots)

    }

}