package com.namu.core.rpg.quest.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.util.playerQuest
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
        val playerQuest = player.playerQuest
        QuestRepository.getQuestList()
                .filter { it.startNpc == npcName }
                .filter { playerQuest.isAvailableQuest(it, player.playerLevel) }
                .map { it.toItemStackWithPlayerQuest(playerQuest) }
                .forEach { inventory.addItem(it) }
    }

}