package com.kkomi.treeisland.plugin.enhance.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class EnhanceInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "강화석을 들고 아이템을 클릭해주세요"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 0, TITLE)

    override fun setBasicFrame() {
        
    }
}