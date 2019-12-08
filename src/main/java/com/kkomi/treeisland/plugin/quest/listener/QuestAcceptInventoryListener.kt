package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestAcceptInventory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestAcceptInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory

        if (!inventory.title.contains("퀘스트 수락")) {
            return
        }

        event.isCancelled = true

        val questName = inventory.title.split(" - ")[1]
        val quest = QuestPlugin.questManager.getQuestToTitle(questName)!!

        with(playerInfo) {
            event.currentItem?.let {
                when (it) {
                    QuestAcceptInventory.acceptItemStack -> {
                        questInfo.acceptQuest(quest)
                        quest.sendAcceptMessage(player)
                        questInfo.save()
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