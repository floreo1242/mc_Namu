package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestListInventory
import com.kkomi.treeisland.plugin.quest.model.QuestAction
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
                    .map { questName -> QuestPlugin.questManager.getQuest(questName)!! }
                    .find { it.endNpc == rightClickedEntityName }
                    ?.let {
                        if (questInfo.inProgressQuestList[it.name]!! >= it.count) {
                            questInfo.completeQuest(it)
                            it.sendCompleteMessage(player)
                            questInfo.save()
                            player.inventory.addItem(*it.rewardItems.toTypedArray())
                            if (it.rewardCommand != "") player.performCommand(it.rewardCommand)
                            return
                        } else {
                            it.sendPurposeMessage(player)
                            return
                        }
                    }
        }

        QuestPlugin.questManager.questList.find { rightClickedEntityName == it.startNpc } ?: return
        QuestListInventory(event.player, rightClickedEntityName).open()
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        if (!QuestPlugin.playerQuestManager.playerQuestUUIDs.contains(player.uniqueId.toString())) {
            QuestPlugin.playerQuestManager.createQuestPlayer(player.uniqueId.toString())
        }
    }

}