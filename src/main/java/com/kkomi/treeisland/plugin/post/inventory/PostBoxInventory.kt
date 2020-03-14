package com.kkomi.treeisland.plugin.post.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.post.model.entity.PlayerPostBox
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class PostBoxInventory(player: Player, playerPostBox: PlayerPostBox) : InventoryManager(player) {

    companion object {
        const val TITLE = "우편함"
        val paneItemStack: ItemStack = createItemStack(Material.STAINED_GLASS_PANE, " ")
        val nextPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Next Page")
        val previousPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Previous Page")
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, TITLE)

    override fun setBasicFrame() {
        (0 until 9).forEach { col -> inventory.setItem(45 + col, paneItemStack) }
        inventory.setItem(48, previousPageItemStack)
        inventory.setItem(50, nextPageItemStack)
    }

}