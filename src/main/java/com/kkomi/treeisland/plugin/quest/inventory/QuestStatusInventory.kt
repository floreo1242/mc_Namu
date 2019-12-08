package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.setLore
import com.kkomi.treeisland.library.inventory.InventoryManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.Quest
import com.kkomi.treeisland.plugin.quest.model.QuestAction
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestStatusInventory(player: Player) : InventoryManager(player) {

    override val title: String
        get() = "퀘스트 상태"

    override val inventory: Inventory = Bukkit.createInventory(null, 9, title)

    override fun setBasicFrame() {
        val playerQuest = player.getPlayerInfo().questInfo
        playerQuest.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
        playerQuest.inProgressQuestList
                .map { QuestPlugin.questManager.getQuest(it.key) }
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
            QuestAction.FARMING_ITEM -> quest.itemStackObject.getDisplay() ?: quest.itemStackObject.type.toString()
            QuestAction.MOVE_LOCATION -> ""
            else -> quest.stringObject
        }
    }

}