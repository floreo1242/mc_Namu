package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.extension.count
import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.library.extension.setItem
import com.kkomi.treeisland.plugin.quest.inventory.QuestAcceptInventory
import com.kkomi.treeisland.plugin.quest.inventory.QuestTalkInventory
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class QuestTalkInventoryListener : Listener {

    private val nowPage = mutableMapOf<String, Int>()
    private val viewQuest = mutableMapOf<String, Quest>()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestTalkInventory.TITLE) {
            return
        }

        val uuid = event.player.uniqueId.toString()

        nowPage[uuid] = 0
        viewQuest[uuid] = QuestRepository.getQuest(data.second)!!
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestTalkInventory.TITLE) {
            return
        }

        event.isCancelled = true

        if (event.rawSlot != 13) {
            return
        }

        val uuid = event.whoClicked.uniqueId.toString()
        nowPage.count(uuid)

        val page = nowPage[uuid]!!
        val quest = viewQuest[uuid]!!

        if (page == quest.talkScriptList.size) {
            val player = event.whoClicked as Player
            player.closeInventory()
            QuestAcceptInventory(player, quest).open()
            return
        }

        inventory.setItem(1, 4, quest.talkScriptList[page].toItemStack())
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != QuestTalkInventory.TITLE) {
            return
        }

        val uuid = event.player.uniqueId.toString()
        nowPage.count(uuid)

        val page = nowPage[uuid]!!
        val script = viewQuest[uuid]!!

        if (page <= script.talkScriptList.size) {
            val player = event.player as Player
            Bukkit.getScheduler().scheduleSyncDelayedTask(Treeisland.instance, {
                QuestTalkInventory(player, script).open()
            }, 5L)
        }
    }

}