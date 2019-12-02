package com.kkomi.treeisland.quest.inventory

import com.kkomi.library.inventory.InventoryManager
import com.kkomi.treeisland.integration.getPlayerInfo
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestListInventory(
        player: Player,
        private val npcName: String
) : InventoryManager(player) {

    override val title: String
        get() = "퀘스트 목록 - $npcName"

    override val inventory: Inventory = Bukkit.createInventory(null, 54, title)

    override fun setBasicFrame() {
        val playerQuest = player.getPlayerInfo().questInfo
        QuestPlugin.questManager.questList
                .filter { it.startNpc == npcName }
                .map { it.toItemStackWithPlayerQuest(playerQuest) }
                .forEach { inventory.addItem(it) }
    }

}