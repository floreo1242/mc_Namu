package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.inventory.QuestAcceptInventory
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestAcceptInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestAcceptInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val questName = data.second
        val quest = QuestRepository.getQuestToTitle(questName)!!

        with(playerInfo) {
            event.currentItem?.let {
                when (it) {
                    QuestAcceptInventory.acceptItemStack -> {
                        questInfo.acceptQuest(quest)
                        quest.sendAcceptMessage(player)
                        PlayerQuestRepository.editPlayerQuest(questInfo)
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

}