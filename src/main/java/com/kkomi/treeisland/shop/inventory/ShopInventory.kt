package com.kkomi.treeisland.shop.inventory

import com.kkomi.library.extension.createItemStack
import com.kkomi.library.extension.setItem
import com.kkomi.library.extension.toMoneyFormat
import com.kkomi.library.inventory.InventoryManager
import com.kkomi.treeisland.integration.PlayerInfo
import com.kkomi.treeisland.shop.eneity.Shop
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShopInventory(player: Player, private val shop: Shop) : InventoryManager(player) {

    companion object {
        val paneItemStack: ItemStack = createItemStack(Material.STAINED_GLASS_PANE, "")
        val nextPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Next Page")
        val previousPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Previous Page")
        fun hasMoneyItemStack(money: Int): ItemStack = createItemStack(Material.GOLD_INGOT, "&6보유금액", listOf("&f${money.toMoneyFormat()}"))
    }

    override fun open() {
        inventory = Bukkit.createInventory(null, 54, "${shop.name}-상점")
        setBasicFrame()
        player.openInventory(inventory)
    }

    override fun setBasicFrame() {
        (0 until 9).forEach { col -> inventory.setItem(4, col, paneItemStack) }
        inventory.setItem(5, 3, previousPageItemStack)
        inventory.setItem(5, 5, nextPageItemStack)
        inventory.setItem(5, 8, hasMoneyItemStack(PlayerInfo(player).moneyInfo.money))
    }
}