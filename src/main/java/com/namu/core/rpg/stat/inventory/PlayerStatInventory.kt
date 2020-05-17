package com.namu.core.rpg.stat.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class PlayerStatInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "플레이어 스텟"

        const val STRENGTH = 10
        const val HEALTH = 11
        const val MANA = 12
        const val WALK_SPEED = 14
        const val CRITICAL_CHANCE = 15
        const val DEXTERITY = 16
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, TITLE)

    override fun setBasicFrame() {
    }

}