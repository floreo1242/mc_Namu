package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.devlibrary.extension.getDisplay
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.removeChatColorCode
import com.kkomi.treeisland.plugin.quest.inventory.QuestCancelInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestStatusInventory
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestStatusInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestStatusInventory.TITLE) {
            return
        }

        event.isCancelled = true

        if (event.currentItem == null || event.currentItem.type == Material.AIR) {
            return
        }

        if (event.rawSlot !in 0..8) {
            return
        }

        QuestCancelInventory(
                event.whoClicked as Player,
                QuestRepository.getQuestToTitle(event.currentItem.getDisplay()!!.removeChatColorCode())!!
        ).open()
    }

}