package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.takeItem
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.entity.OtherItem
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestListInventory
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

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
                            try {
                                player.inventory.takeItem(OtherItemRepository.getItem(it.stringObject)!!.toItemStack(), it.count)
                            } catch (exception: Exception) {
                                player.sendErrorMessage("에러가 발생하였습니다. 관리자에게 문의주세요. ErrorCode : Not Found Other Item")
                            }

                            // PlayerQuest
                            questInfo.completeQuest(it)
                            it.sendCompleteMessage(player)
                            PlayerQuestRepository.editPlayerQuest(questInfo)

                            // Reward
                            player.inventory.addItem(
                                    *it.rewardItems.map { code ->
                                        OtherItemRepository.getItem(code)?.toItemStack() ?: ItemStack(Material.AIR)
                                    }.toTypedArray()
                            )
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