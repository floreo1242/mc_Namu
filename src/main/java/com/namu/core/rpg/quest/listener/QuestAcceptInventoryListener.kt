package com.namu.core.rpg.quest.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.namu.core.rpg.quest.inventory.QuestAcceptInventory
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.util.edit
import com.namu.core.rpg.quest.util.playerQuest
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestAcceptInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        val playerQuest = player.playerQuest
        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestAcceptInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val questName = data.second
        val quest = QuestRepository.getQuestToTitle(questName)!!

        event.currentItem?.let {
            when (it) {
                QuestAcceptInventory.acceptItemStack -> {
                    playerQuest.acceptQuest(quest)
                    quest.sendAcceptMessage(player)
                    playerQuest.edit()
                    player.closeInventory()
                }
                QuestAcceptInventory.disposeItemStack -> {
                    player.closeInventory()
                    quest.sendDisposeMessage(player)
                }
            }
        }

    }

}