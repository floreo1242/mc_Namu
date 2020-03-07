package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.inventory.InventoryManager
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestTalkInventory(
        player: Player,
        val quest: Quest
) : InventoryManager(player) {

    companion object {
        const val TITLE = "스크립트 대화"

        const val ITEM_LOCATION = 13
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 27, "$TITLE - ${quest.name}")

    override fun setBasicFrame() {
        inventory.setItem(0, createItemStack(Material.GOLD_AXE,"", listOf(),1,2))
        inventory.setItem(ITEM_LOCATION, quest.talkScriptList[0].toItemStack())
    }

}