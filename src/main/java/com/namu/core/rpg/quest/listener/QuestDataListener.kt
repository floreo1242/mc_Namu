package com.namu.core.rpg.quest.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.rpg.quest.inventory.QuestListInventory
import com.namu.core.rpg.quest.model.PlayerQuestRepository
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.model.entity.PlayerQuest
import com.namu.core.rpg.quest.model.entity.QuestAction
import com.namu.core.rpg.quest.util.playerQuest
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot

class QuestDataListener : Listener {

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEntityEvent) {

        if (event.hand == EquipmentSlot.OFF_HAND) {
            return
        }

        val rightClickedEntityName = event.rightClicked.name ?: return

        with(event.player) {

            playerQuest.checkQuestAmount(QuestAction.FARMING_ITEM) { true }

            playerQuest.inProgressQuestList.keys
                    .map { questName -> QuestRepository.getQuest(questName)!! }
                    .find { it.endNpc == rightClickedEntityName }
                    ?.let { quest ->

                        if (!playerQuest.isCompletedQuest(quest)) {
                            quest.sendPurposeMessage(this)
                            event.isCancelled = true
                            return
                        }

                        if (inventory.count { it == null || it.type == Material.AIR } < quest.reward.items.size) {
                            sendErrorMessage("인벤토리 공간이 부족합니다!")
                            return//
                        }

                        playerQuest.completeQuest(quest)
                        playerQuest.takeQuestItems(this, quest)
                        playerQuest.receiveRewards(this, quest)
                        quest.sendCompleteMessage(this)
                        PlayerQuestRepository.editPlayerQuest(playerQuest)

                        sendTitle(
                                "[ 퀘스트 완료 ]",
                                quest.name,
                                10,
                                30,
                                10
                        )
                        return
                    }

            // Open Quest Check
            QuestRepository.getQuestList().find { rightClickedEntityName == it.startNpc } ?: return
            QuestListInventory(event.player, rightClickedEntityName).open()
        }
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        if (PlayerQuestRepository.getPlayerQuest(player.uniqueId.toString()) == null) {
            PlayerQuestRepository.createPlayerQuest(PlayerQuest(player.uniqueId.toString(), mutableListOf(), mutableMapOf()))
        }
    }

}