package com.namu.core.rpg.quest.listener

import com.kkomi.devlibrary.extension.*
import com.namu.core.rpg.quest.inventory.QuestListInventory
import com.namu.core.rpg.quest.inventory.QuestTalkInventory
import com.namu.core.rpg.quest.model.QuestRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestListInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.view
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
            Material.WRITABLE_BOOK -> {
                player.closeInventory()
                QuestTalkInventory(player, quest).open()
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