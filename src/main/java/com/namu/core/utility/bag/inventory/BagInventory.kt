package com.namu.core.utility.bag.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.utility.bag.model.entity.Bag
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class BagInventory(player: Player, bag: Bag) : InventoryManager(player) {

    companion object {
        const val TITLE = "가방"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, bag.level * 9, TITLE)

    override fun setBasicFrame() {
    }

}