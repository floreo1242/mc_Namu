package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory

class QuestRewardInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 보상"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "$TITLE - ${quest.name}")

    override fun setBasicFrame() {
        inventory.addItem(*quest.rewardItems.toTypedArray())
    }

}