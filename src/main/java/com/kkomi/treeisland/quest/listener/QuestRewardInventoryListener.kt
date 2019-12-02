package com.kkomi.treeisland.quest.listener

import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class QuestRewardInventoryListener : Listener {

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val inventoryTitleInfo = event.inventory.title.split(" - ")
        val inventoryType = inventoryTitleInfo[0]

        if (inventoryType != "퀘스트 보상") {
            return
        }

        val quest = QuestPlugin.questManager.getQuestToTitle(inventoryTitleInfo[1])!!
        quest.rewardItems = event.inventory.storageContents.toList().filterNotNull().filter { it.type != Material.AIR }
        quest.save()
        (event.player as Player).sendInfoMessage("퀘스트 보상 아이템을 설정하였습니다.")
    }

}