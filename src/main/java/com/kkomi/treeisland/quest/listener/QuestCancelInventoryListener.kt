package com.kkomi.treeisland.quest.listener

import com.kkomi.treeisland.integration.getPlayerInfo
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestCancelInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory

        if (!inventory.title.contains("퀘스트 취소")) {
            return
        }

        event.isCancelled = true

        when (event.slot) {
            15 -> {
                val quest = QuestPlugin.questManager.getQuestToTitle(inventory.title.split(" - ")[1])!!
                playerInfo.questInfo.throwUpQuest(quest)
                playerInfo.questInfo.save()
                playerInfo.sendInfoMessage("퀘스트를 취소하였습니다.")
                playerInfo.player.closeInventory()
            }
            else -> return
        }
    }

}