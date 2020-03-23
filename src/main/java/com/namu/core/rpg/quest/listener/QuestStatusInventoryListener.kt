package com.namu.core.rpg.quest.listener

import com.kkomi.devlibrary.extension.getDisplay
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.removeChatColorCode
import com.namu.core.rpg.quest.inventory.QuestCancelInventory
import com.namu.core.rpg.quest.inventory.QuestStatusInventory
import com.namu.core.rpg.quest.model.QuestRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestStatusInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestStatusInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val item = event.currentItem ?: return

        if (item.type == Material.AIR) {
            return
        }

        if (event.rawSlot !in 0..8) {
            return
        }

        QuestCancelInventory(
                event.whoClicked as Player,
                QuestRepository.getQuestToTitle(item.getDisplay()!!.removeChatColorCode())!!
        ).open()
    }

}