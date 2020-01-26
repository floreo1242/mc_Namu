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
import com.nisovin.magicspells.util.InventoryUtil
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
                    ?.let { quest ->

                        if (!questInfo.isCompletedQuest(quest)) {
                            quest.sendPurposeMessage(player)
                            event.isCancelled = true
                            return
                        }

                        if (player.inventory.count { it == null || it.type == Material.AIR } < quest.reward.items.size) {
                            player.sendErrorMessage("인벤토리 공간이 부족합니다!")
                            return//
                        }

                        questInfo.completeQuest(quest)
                        questInfo.takeQuestItems(player, quest)
                        questInfo.receiveRewards(player, quest)
                        quest.sendCompleteMessage(player)
                        PlayerQuestRepository.editPlayerQuest(questInfo)

                        player.sendTitle(
                                "[ 퀘스트 완료 ]",
                                quest.name,
                                10,
                                30,
                                10
                        )
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
            PlayerQuestRepository.addPlayerQuest(PlayerQuest(player.uniqueId.toString(), mutableListOf(), mutableMapOf()))
        }
    }

}