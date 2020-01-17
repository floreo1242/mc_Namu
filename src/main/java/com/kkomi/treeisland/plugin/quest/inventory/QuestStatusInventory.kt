package com.kkomi.treeisland.plugin.quest.inventory

import com.kkomi.treeisland.library.extension.createItemStack
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
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class QuestStatusInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "퀘스트 상태"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 9, "$TITLE - ${player.name}")

    override fun setBasicFrame() {
        val playerQuest = player.getPlayerInfo().questInfo
        playerQuest.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
        playerQuest.inProgressQuestList
                .mapKeys { map -> QuestRepository.getQuest(map.key)!! }
                .forEach { (quest, playerQuestObjectiveList) ->

                    val lore = mutableListOf<String>()

                    playerQuestObjectiveList.forEach { playerQuestObjective ->
                        lore.add("&f${playerQuestObjective.action.description.format(playerQuestObjective.target, playerQuestObjective.targetAmount)} ${if (playerQuestObjective.isComplete()) "&a완료" else "&c미완료"}")
                    }

                    lore.add("")
                    lore.add("&f퀘스트 진행도 : ${playerQuestObjectiveList.sumBy { it.amount }} / ${quest.questObjectiveList.sumBy { it.amount }}")

                    inventory.addItem(createItemStack(Material.PAPER, quest.name, lore))

                }
    }

}