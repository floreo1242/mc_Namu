package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestListInventory(
        player: Player,
        private val npcName: String
) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 목록"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, "$TITLE - $npcName")

    override fun setBasicFrame() {
        val playerQuest = player.getPlayerInfo().questInfo
        QuestRepository.getQuestList()
                .filter { it.startNpc == npcName }
                .map { it.toItemStackWithPlayerQuest(playerQuest) }
                .forEach { inventory.addItem(it) }
    }

}