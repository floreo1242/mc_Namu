package com.namu.core.rpg.quest.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.quest.inventory.QuestCancelInventory
import com.namu.core.rpg.quest.model.PlayerQuestRepository
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.model.entity.QuestType
import com.namu.core.rpg.quest.util.playerQuest
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class QuestCancelInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestCancelInventory.TITLE) {
            return
        }

        event.isCancelled = true

        when (event.slot) {
            14 -> {
                val quest = QuestRepository.getQuestToTitle(data.second)!!

                if (quest.type == QuestType.MAIN) {
                    player.sendInfoMessage("메인 퀘스트는 취소 할 수 없습니다.")
                    return
                }

                player.playerQuest.throwUpQuest(quest)
                PlayerQuestRepository.editPlayerQuest(player.playerQuest)
                player.sendInfoMessage("퀘스트를 취소하였습니다.")
                player.closeInventory()
            }
            else -> return
        }
    }

}