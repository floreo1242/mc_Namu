package com.kkomi.treeisland.quest.listener

import com.kkomi.library.extension.getDisplay
import com.kkomi.library.extension.removeChatColorCode
import com.kkomi.library.extension.replaceChatColorCode
import com.kkomi.library.extension.setLore
import com.kkomi.treeisland.integration.getPlayerInfo
import com.kkomi.treeisland.quest.QuestPlugin
import com.kkomi.treeisland.quest.inventory.QuestCancelInventory
import com.kkomi.treeisland.quest.model.QuestAction
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent

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