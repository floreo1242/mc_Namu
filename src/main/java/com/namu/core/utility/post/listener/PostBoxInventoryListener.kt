package com.namu.core.utility.post.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.count
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.inventory.InventoryMessage
import com.namu.core.utility.post.inventory.PostBoxInventory
import com.namu.core.utility.post.util.edit
import com.namu.core.utility.post.util.playerPostBox
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class PostBoxInventoryListener : Listener {

    private val shopPageHash: MutableMap<String, Int> = mutableMapOf()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventory = event.view

        if (inventory.title != PostBoxInventory.TITLE) {
            return
        }

        val playerPostBox = (event.player as Player).playerPostBox
        val pageList = PageList(45, playerPostBox.postItems)

        shopPageHash[event.player.uniqueId.toString()] = 0
        (0 until 45)
                .forEach {
                    inventory.setItem(it, null)
                }
        pageList.getPage(shopPageHash[event.player.uniqueId.toString()] ?: 0)
                .forEachIndexed { index, itemStack ->
                    inventory.setItem(index, itemStack)
                }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.view

        if (inventory.title != PostBoxInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = (event.whoClicked as Player)
        val playerPostBox = player.playerPostBox
        val pageList = PageList(45, playerPostBox.postItems)
        val slot = event.rawSlot
        val page = shopPageHash[player.uniqueId.toString()] ?: 0

        when {
            slot == 48 -> {
                if (page == 0) {
                    player.sendErrorMessage(InventoryMessage.PAGE_FIRST)
                    return
                }
                shopPageHash.count(player.uniqueId.toString(), -1)
            }
            slot == 50 -> {
                if (page == pageList.lastPageIndex) {
                    player.sendErrorMessage(InventoryMessage.PAGE_LAST)
                    return
                }
                shopPageHash.count(player.uniqueId.toString(), 1)
            }
            (0 until 45).contains(slot) -> {
                event.currentItem ?: return
                playerPostBox.apply { postItems.removeAt((page * 45) + slot) }.edit()
                player.inventory.addItem(event.currentItem)
                player.sendInfoMessage("물건을 수령하였습니다.")
            }
        }

        (0 until 45)
                .forEach {
                    inventory.setItem(it, null)
                }
        pageList.getPage(page)
                .forEachIndexed { index, itemStack ->
                    inventory.setItem(index, itemStack)
                }
    }

}