package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.library.extension.takeItem
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestListInventory
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
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

        with(event.player.getPlayerInfo()) {
            questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }

            questInfo.inProgressQuestList.keys
                    .map { questName -> QuestRepository.getQuest(questName)!! }
                    .find { it.endNpc == rightClickedEntityName }
                    ?.let {
                        if (questInfo.inProgressQuestList[it.name]!! >= it.count) {

                            // TakeItem
                            player.inventory.takeItem(it.itemStackObject, it.count)

                            // PlayerQuest
                            questInfo.completeQuest(it)
                            it.sendCompleteMessage(player)
                            PlayerQuestRepository.editPlayerQuest(questInfo)

                            // Reward
                            player.inventory.addItem(*it.rewardItems.toTypedArray())
                            if (it.rewardCommand != "") player.performCommand(it.rewardCommand)

                            return
                        } else {
                            it.sendPurposeMessage(player)
                            return
                        }
                    }
        }

        // Open Quest Check
        QuestRepository.getQuestList().find { rightClickedEntityName == it.startNpc } ?: return
        QuestListInventory(event.player, rightClickedEntityName).open()
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        if (PlayerQuestRepository.getPlayerQuest(player.uniqueId.toString()) == null) {
            PlayerQuestRepository.addPlayerQuest(PlayerQuest(player.uniqueId.toString(), mutableListOf(), mutableMapOf()))
        }
    }

}