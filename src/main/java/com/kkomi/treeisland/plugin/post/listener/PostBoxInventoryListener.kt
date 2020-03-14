package com.kkomi.treeisland.plugin.post.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.count
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.inventory.InventoryMessage
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.post.inventory.PostBoxInventory
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class PostBoxInventoryListener : Listener {

    private val shopPageHash: MutableMap<String, Int> = mutableMapOf()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventory = event.inventory

        if (inventory.title != PostBoxInventory.TITLE) {
            return
        }

        val playerPostBox = (event.player as Player).getPlayerInfo().playerPostBox
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
        val inventory = event.inventory

        if (inventory.title != PostBoxInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = (event.whoClicked as Player)
        val playerPostBox = player.getPlayerInfo().playerPostBox
        val pageList = PageList(45, playerPostBox.postItems)
        val slot = event.rawSlot
        val page = shopPageHash[player.uniqueId.toString()] ?: 0

        when {
            slot == 48 -> {
                if (page == 1) {
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
                playerPostBox.postItems.removeAt((page * 45) + slot)
                player.inventory.addItem(event.currentItem)
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