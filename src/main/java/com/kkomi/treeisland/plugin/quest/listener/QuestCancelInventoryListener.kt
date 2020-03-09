package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.inventory.QuestCancelInventory
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestCancelInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestCancelInventory.TITLE) {
            return
        }

        event.isCancelled = true

        when (event.slot) {
            14 -> {
                val quest = QuestRepository.getQuestToTitle(data.second)!!
                playerInfo.questInfo.throwUpQuest(quest)
                PlayerQuestRepository.editPlayerQuest(playerInfo.questInfo)
                playerInfo.sendInfoMessage("퀘스트를 취소하였습니다.")
                playerInfo.player.closeInventory()
            }
            else -> return
        }
    }

}