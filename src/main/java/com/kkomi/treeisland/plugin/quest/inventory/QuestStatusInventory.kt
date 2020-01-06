package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.setLore
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.OtherItemRepository
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestStatusInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 상태"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 9, TITLE)

    override fun setBasicFrame() {
        val playerQuest = player.getPlayerInfo().questInfo
        playerQuest.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
        playerQuest.inProgressQuestList
                .map { QuestRepository.getQuest(it.key) }
                .map {
                    it!!.toItemStackWithPlayerQuest(playerQuest)
                            .setLore(listOf(
                                    "&f${it.action.description.format(getQuestObject(it), it.count)}",
                                    "",
                                    "&f퀘스트 진행도 : ${playerQuest.inProgressQuestList[it.name]} / ${it.count}"
                            ))
                }.forEach { inventory.addItem(it) }
    }

    private fun getQuestObject(quest: Quest): String {
        return when (quest.action) {
            QuestAction.FARMING_ITEM -> OtherItemRepository.getItem(quest.stringObject)!!.toItemStack().getDisplay()!!
            QuestAction.MOVE_LOCATION -> ""
            else -> quest.stringObject
        }
    }

}