package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.Quest
import org.bukkit.Bukkit
import org.bukkit.entity.Player
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