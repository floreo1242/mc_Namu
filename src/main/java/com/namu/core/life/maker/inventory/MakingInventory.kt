package com.namu.core.life.maker.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.economy.auction.inventory.AuctionInventory
import com.namu.core.life.maker.model.entity.Recipe
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

class MakingInventory(
        player: Player,
        val recipe: Recipe
) : InventoryManager(player) {

    companion object {
        const val TITLE = "재료를 넣어주세요"

        val EMPTY = createItemStack(Material.BLACK_STAINED_GLASS_PANE, "")
        val OK = createItemStack(Material.OAK_SIGN, "&a제작 하기")
        val CANCEL = createItemStack(Material.OAK_SIGN, "&c취소 하기")
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 9, "$TITLE - ${recipe.name}")

    override fun setBasicFrame() {
        inventory.setItem(0, EMPTY)
        inventory.setItem(6, EMPTY)
        inventory.setItem(7, OK)
        inventory.setItem(8, CANCEL)
    }

}