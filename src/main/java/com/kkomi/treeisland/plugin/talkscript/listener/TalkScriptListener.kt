package com.kkomi.treeisland.plugin.talkscript.listener

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.extension.count
import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.library.extension.setItem
import com.kkomi.treeisland.plugin.talkscript.inventory.TalkScriptInventory
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptRepository
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class TalkScriptListener : Listener {

    private val nowPage = mutableMapOf<String, Int>()
    private val viewScript = mutableMapOf<String, TalkScript>()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        println(event.inventory.getServerTitleInfo())

        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != TalkScriptInventory.TITLE) {
            return
        }

        val uuid = event.player.uniqueId.toString()

        nowPage[uuid] = 0
        viewScript[uuid] = TalkScriptRepository.getTalkScript(data.second)!!
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != TalkScriptInventory.TITLE) {
            return
        }

        event.isCancelled = true

        if (event.rawSlot != 13) {
            return
        }

        val uuid = event.whoClicked.uniqueId.toString()
        nowPage.count(uuid)

        val page = nowPage[uuid]!!
        val script = viewScript[uuid]!!

        if (page == script.dialogList.size) {
            val player = event.whoClicked as Player
            player.performCommand(script.runCommand)
            player.closeInventory()
            return
        }

        inventory.setItem(1, 4, script.dialogList[page].toItemStack())
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != TalkScriptInventory.TITLE) {
            return
        }

        val uuid = event.player.uniqueId.toString()
        nowPage.count(uuid)

        val page = nowPage[uuid]!!
        val script = viewScript[uuid]!!

        if (page <= script.dialogList.size) {
            val player = event.player as Player
            Bukkit.getScheduler().scheduleSyncDelayedTask(Treeisland.instance, {
                TalkScriptInventory(player, script).open()
            }, 5L)
        }
    }

}