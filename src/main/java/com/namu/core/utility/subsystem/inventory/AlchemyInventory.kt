package com.namu.core.utility.subsystem.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class AlchemyInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "연금"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, TITLE)

    override fun setBasicFrame() {

    }

}