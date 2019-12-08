package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.removeChatColorCode
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestCancelInventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestStatusInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.inventory

        if (!inventory.title.contains("퀘스트 상태")) {
            return
        }

        event.isCancelled = true

        if (event.currentItem == null || event.currentItem.type == Material.AIR) {
            return
        }

        QuestCancelInventory(
                event.whoClicked as Player,
                QuestPlugin.questManager.getQuestToTitle(event.currentItem.getDisplay()!!.removeChatColorCode())!!
        ).open()
    }

}