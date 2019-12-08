package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.extension.*
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.Quest
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
        val acceptItemStack: ItemStack = createItemStack(Material.WOOL, "&9수락", listOf("&f클릭 시 퀘스트를 수락합니다."), durability = 11)
        val disposeItemStack: ItemStack = createItemStack(Material.WOOL, "&c거절", listOf("&f클릭 시 퀘스트를 거절합니다."), durability = 14)
    }

    override val title: String
        get() = "퀘스트 수락 - ${quest.title}"

    override val inventory: Inventory = Bukkit.createInventory(null, 45, title)

    override fun setBasicFrame() {
        inventory.setItem(1, 4, quest.toItemStack())
        inventory.setItem(3, 2, acceptItemStack)
        inventory.setItem(3, 6, disposeItemStack)
    }

}