package com.kkomi.treeisland.plugin.shop.inventory

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.extension.setItem
import com.kkomi.treeisland.library.extension.toMoneyFormat
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ShopInventory(player: Player, private val shop: Shop) : InventoryManager(player) {

    companion object {
        const val TITLE = "상점"
        val paneItemStack: ItemStack = createItemStack(Material.STAINED_GLASS_PANE, " ")
        val nextPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Next Page")
        val previousPageItemStack: ItemStack = createItemStack(Material.SIGN, "&6Previous Page")
        fun hasMoneyItemStack(money: Long): ItemStack = createItemStack(Material.GOLD_INGOT, "&6보유금액", listOf("&f${money.toMoneyFormat()}"))
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, "$TITLE - ${shop.name}")

    override fun setBasicFrame() {
        (0 until 9).forEach { col -> inventory.setItem(4, col, ShopInventory.paneItemStack) }
        inventory.setItem(5, 3, previousPageItemStack)
        inventory.setItem(5, 5, nextPageItemStack)
        inventory.setItem(5, 8, hasMoneyItemStack(PlayerInfo(player).moneyInfo.money))
    }

}