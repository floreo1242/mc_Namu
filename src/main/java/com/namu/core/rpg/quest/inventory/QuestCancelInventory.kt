package com.namu.core.rpg.quest.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.rpg.quest.model.entity.Quest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestCancelInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 취소"
        val cancelItemStack = createItemStack(Material.RED_WOOL, "&c취소", listOf("&f- 퀘스트를 취소합니다."))
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${quest.title}")

    override fun setBasicFrame() {
        inventory.setItem(10, quest.toItemStack())
        inventory.setItem(14, cancelItemStack)
        inventory.setItem(0, createItemStack(Material.GOLDEN_AXE,"", listOf(),1,5))
    }

}