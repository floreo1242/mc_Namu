package com.namu.core.economy.auction.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class AuctionInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "경매장"

        val PREVIOUS_PAGE = createItemStack(Material.OAK_SIGN, "&6다음 페이지")
        val NEXT_PAGE = createItemStack(Material.OAK_SIGN, "&6다음 페이지")
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, TITLE)

    override fun setBasicFrame() {
        inventory.setItem(48, PREVIOUS_PAGE)
        inventory.setItem(50, NEXT_PAGE)
    }

}