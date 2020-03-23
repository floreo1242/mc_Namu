package com.namu.core.economy.shop.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.extension.toMoneyFormat
import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.economy.shop.model.entity.Shop
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ShopInventory(player: Player, private val shop: Shop) : InventoryManager(player) {

    companion object {
        const val TITLE = "상점"
        val paneItemStack: ItemStack = createItemStack(Material.BLACK_STAINED_GLASS_PANE, " ")
        val nextPageItemStack: ItemStack = createItemStack(Material.OAK_SIGN, "&6Next Page")
        val previousPageItemStack: ItemStack = createItemStack(Material.OAK_SIGN, "&6Previous Page")
        fun hasMoneyItemStack(money: Long): ItemStack = createItemStack(Material.GOLD_INGOT, "&6보유금액", listOf("&f${money.toMoneyFormat()}"))
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, "$TITLE - ${shop.name}")

    override fun setBasicFrame() {
        (0 until 9).forEach { col -> inventory.setItem(36 + col, paneItemStack) }
        inventory.setItem(48, previousPageItemStack)
        inventory.setItem(50, nextPageItemStack)
        inventory.setItem(53, hasMoneyItemStack(player.playerMoney.money))
    }

}