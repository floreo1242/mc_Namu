package com.kkomi.treeisland.plugin.stat.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class PlayerStatInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "플레이어 스텟"

        const val HEAD = 8
        const val STRENGTH = 11
        const val DEXTERITY = 12
        const val INTELLIGENCE = 13
        const val DEFENSE = 14
        const val AGILITY = 15
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${player.name}")

    override fun setBasicFrame() {

    }

}