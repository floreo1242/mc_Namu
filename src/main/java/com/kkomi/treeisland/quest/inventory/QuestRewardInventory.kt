package com.kkomi.treeisland.quest.inventory

import com.kkomi.library.inventory.InventoryManager
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

class QuestRewardInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    override val title: String
        get() = "퀘스트 보상 - ${quest.title}"

    override val inventory: Inventory = Bukkit.createInventory(null, InventoryType.HOPPER, title)

    override fun setBasicFrame() {
        inventory.addItem(*quest.rewardItems.toTypedArray())
    }

}