package com.kkomi.treeisland.quest.listener

import com.kkomi.library.extension.*
import com.kkomi.treeisland.integration.PlayerInfo
import com.kkomi.treeisland.integration.getPlayerInfo
import com.kkomi.treeisland.quest.QuestPlugin
import com.kkomi.treeisland.quest.inventory.QuestAcceptInventory
import com.kkomi.treeisland.quest.inventory.QuestListInventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class QuestListInventoryListener : Listener {

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventoryTitleInfo = event.inventory.title.split(" - ")
        val inventoryType = inventoryTitleInfo[0]

        if (inventoryType != "퀘스트 목록") {
            return
        }

        val npcName = inventoryTitleInfo[1]


    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventoryTitleInfo = event.inventory.title.split(" - ")
        val inventoryType = inventoryTitleInfo[0]

        if (inventoryType != "퀘스트 목록") {
            return
        }

        event.isCancelled = true

        val player = event.whoClicked as Player
        val currentItem = event.currentItem ?: return

        if (currentItem.type == Material.AIR) {
            return
        }

        val quest = QuestPlugin.questManager.getQuestToTitle(currentItem.getDisplay()!!.removeChatColorCode()) ?: return

        when (currentItem.type) {
            Material.BOOK_AND_QUILL -> {
                player.closeInventory()
                QuestAcceptInventory(player, quest).open()
            }
            Material.BOOK -> {
                player.closeInventory()
                player.sendInfoMessage("${quest.startNpc} : ${quest.purposeMessage}")
            }
            Material.ENCHANTED_BOOK -> {
                player.closeInventory()
                player.sendErrorMessage("이미 완료한 퀘스트입니다.")
            }
            else -> return
        }
    }

}