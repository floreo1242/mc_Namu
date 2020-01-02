package com.kkomi.treeisland.plugin.stat.inventory

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class PlayerStatInventory(player: Player) : InventoryManager(player) {

    companion object {
        val TITLE = "플레이어 스텟"

        val HEAD = 13
        val STRENGTH = 19
        val MIND = 21
        val NATURE = 23
        val STAMINA = 25
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 36, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        for (i in 0 until 36) {
            inventory.setItem(i, createItemStack(Material.STAINED_GLASS_PANE, "", listOf(), durability = 15))
        }

        val statInfo = player.getPlayerInfo().statInfo

        inventory.setItem(HEAD, createItemStack(
                Material.SKULL_ITEM,
                "${player.name}님의 스텟 정보",
                listOf(
                        *statInfo.pickingStat.map {
                            "&f${it.key.strName} : ${it.value}"
                        }.toTypedArray(),
                        "",
                        "&f잔여 포인트 : ${statInfo.leftPoint}"
                ),
                durability = 3))

        inventory.setItem(STRENGTH, createItemStack(Material.INK_SACK, "&6근력", listOf(), durability = 14))
        inventory.setItem(MIND, createItemStack(Material.INK_SACK, "&b정신", listOf(), durability = 5))
        inventory.setItem(NATURE, createItemStack(Material.INK_SACK, "&a근성", listOf(), durability = 10))
        inventory.setItem(STAMINA, createItemStack(Material.INK_SACK, "&c체력", listOf(), durability = 1))

    }

}