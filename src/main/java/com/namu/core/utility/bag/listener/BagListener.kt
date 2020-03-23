package com.namu.core.utility.bag.listener

import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.utility.bag.inventory.BagInventory
import com.namu.core.utility.bag.model.BagRepository
import com.namu.core.utility.bag.model.entity.Bag
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEvent

class BagListener : Listener {

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val bagItemMeta = event.player.inventory.itemInMainHand.getNBTTagCompound(Bag.ItemMeta::class.java) ?: return
        val bag = BagRepository.getBag(bagItemMeta.bagUUID) ?: return

        if (event.action == Action.LEFT_CLICK_BLOCK || event.action == Action.LEFT_CLICK_AIR) {
            return
        }

        event.isCancelled = true
        BagInventory(event.player, bag).open()
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.view

        if (inventory.title != BagInventory.TITLE) {
            return
        }

        val currentItem = event.currentItem ?: return
        val cursorItem = event.cursor ?: return

        if (currentItem.getNBTTagCompound(Bag.ItemMeta::class.java) != null || cursorItem.getNBTTagCompound(Bag.ItemMeta::class.java) != null) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventory = event.view

        if (inventory.title != BagInventory.TITLE) {
            return
        }

        val bagItemMeta = event.player.inventory.itemInMainHand.getNBTTagCompound(Bag.ItemMeta::class.java) ?: return
        val bag = BagRepository.getBag(bagItemMeta.bagUUID) ?: return

        inventory.topInventory.storageContents = bag.itemList.toTypedArray()
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val inventory = event.view

        if (inventory.title != BagInventory.TITLE) {
            return
        }

        val itemStack = event.player.inventory.itemInMainHand

        val bagItemMeta = itemStack.getNBTTagCompound(Bag.ItemMeta::class.java) ?: return
        val bag = BagRepository.getBag(bagItemMeta.bagUUID) ?: return

        bag.itemList = inventory.topInventory.storageContents.toList()
        BagRepository.editBag(bag)

    }

}