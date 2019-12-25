package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.inventory.QuestListInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestRewardInventory
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

class QuestRewardInventoryListener : Listener {

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestRewardInventory.TITLE) {
            return
        }

        val quest = QuestRepository.getQuest(data.second)!!
        quest.rewardItems = event.inventory.storageContents.toList().filterNotNull().filter { it.type != Material.AIR }
        QuestRepository.editQuest(quest)
        (event.player as Player).sendInfoMessage(QuestMessage.QUEST_SET_REWARD_ITEMS)
    }

}