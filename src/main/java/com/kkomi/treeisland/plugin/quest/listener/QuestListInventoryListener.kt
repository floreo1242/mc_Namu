package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.library.extension.*
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestAcceptInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestCancelInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestListInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestTalkInventory
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class QuestListInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestListInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = event.whoClicked as Player
        val currentItem = event.currentItem ?: return

        if (currentItem.type == Material.AIR) {
            return
        }

        val quest = QuestRepository.getQuestToTitle(currentItem.getDisplay()!!.removeChatColorCode()) ?: return

        when (currentItem.type) {
            Material.BOOK_AND_QUILL -> {
                player.closeInventory()
                QuestTalkInventory(player, quest).open()
            }
            Material.BOOK -> {
                player.closeInventory()
                player.sendInfoMessage("${quest.startNpc} : ${quest.purposeMessage}")
            }
            Material.ENCHANTED_BOOK -> {
                player.closeInventory()
                player.sendErrorMessage("이미 완료한 퀘스트입니다.") // TODO Message
            }
            else -> return
        }
    }

}