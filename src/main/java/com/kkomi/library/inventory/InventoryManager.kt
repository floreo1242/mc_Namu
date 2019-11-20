package com.kkomi.library.inventory

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

abstract class InventoryManager {

    lateinit var inventory: Inventory
    val player: Player

    constructor(player: Player) {
        this.player = player
    }

    abstract fun open()

    abstract fun setBasicFrame()

}