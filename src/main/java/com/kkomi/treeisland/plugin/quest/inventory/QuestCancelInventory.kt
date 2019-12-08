package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.extension.setItem
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.Quest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestCancelInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    companion object {
        val cancelItemStack = createItemStack(Material.WOOL, "&c취소", listOf("&f- 퀘스트를 취소합니다."), durability = 14)
    }

    override val title: String
        get() = "퀘스트 취소 - ${quest.title}"

    override val inventory: Inventory = Bukkit.createInventory(null, 27, title)

    override fun setBasicFrame() {
        inventory.setItem(1, 2, quest.toItemStack())
        inventory.setItem(1, 6, cancelItemStack)
    }

}