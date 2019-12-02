package com.kkomi.library.inventory

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory

abstract class InventoryManager(
        val player: Player
) {

    abstract val title: String
    abstract val inventory: Inventory

    open fun open() {
        setBasicFrame()
        player.openInventory(inventory)
    }

    abstract fun setBasicFrame()

}