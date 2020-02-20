package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.devlibrary.extension.*
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class QuestAcceptInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 수락"

        val acceptItemStack: ItemStack = createItemStack(Material.WOOL, "&9수락", listOf("&f클릭 시 퀘스트를 수락합니다."), durability = 11)
        val disposeItemStack: ItemStack = createItemStack(Material.WOOL, "&c거절", listOf("&f클릭 시 퀘스트를 거절합니다."), durability = 14)
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 45, "$TITLE - ${quest.title}")

    override fun setBasicFrame() {
        inventory.setItem(1, 4, quest.toItemStack())
        inventory.setItem(3, 2, acceptItemStack)
        inventory.setItem(3, 6, disposeItemStack)
    }

}