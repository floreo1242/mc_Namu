package com.kkomi.treeisland.plugin.shop.listener

import com.kkomi.treeisland.library.extension.*
import com.kkomi.treeisland.library.message.InventoryMessage
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import com.kkomi.treeisland.plugin.shop.inventory.ShopInventory
import com.kkomi.treeisland.plugin.shop.model.KeywordShopRepository
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.Inventory

class ShopInventoryListener : Listener {
    private val shopPageHash: MutableMap<String, Int> = mutableMapOf()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {

        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != ShopInventory.TITLE) {
            return
        }

        val shop = ShopRepository.getShop(data.second)!!
        shopPageHash[(event.player as Player).uniqueId.toString()] = 1
        loadStuff(inventory, shop, 1)

    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.inventory
        val slot = event.rawSlot
        val playerInfo = PlayerInfo(event.whoClicked as Player)
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != ShopInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val playerMoney = playerInfo.moneyInfo
        val shop = ShopRepository.getShop(data.second)!!
        val currentItem = if (event.currentItem == null || event.currentItem.isAir()) return else event.currentItem
        val uuid = playerInfo.player.uniqueId.toString()
        val page = shopPageHash[uuid]!!

        when {
            slot == 48 -> {
                if (page == 1) {
                    playerInfo.sendErrorMessage(InventoryMessage.PAGE_FIRST)
                    return
                }
                shopPageHash.count(uuid)
                loadStuff(inventory, shop, page)
            }
            slot == 50 -> {
                if (page == shop.getLastPageNum()) {
                    playerInfo.sendErrorMessage(InventoryMessage.PAGE_LAST)
                    return
                }
                shopPageHash.count(uuid, -1)
                loadStuff(inventory, shop, page)
            }
            // Shop Area
            (0..35).contains(slot) -> {
                val stuff = shop.getStuffList(page)[slot]
                if (playerMoney.money < stuff.price) {
                    playerInfo.sendErrorMessage("소지금이 부족합니다.")
                    return
                }
                if (playerInfo.player.inventory.storageContents.filter { it == null }.count() == 0) {
                    playerInfo.sendErrorMessage(InventoryMessage.INSUFFICIENCY_SPACE)
                    return
                }
                playerMoney.takeMoney(stuff.price)
                playerMoney.save()
                playerInfo.player.inventory.addItem(stuff.itemStack)
                playerInfo.sendInfoMessage(ShopMessage.BUY_ITEM)
            }
            // Player Inventory Area
            (54..89).contains(slot) -> {
                val stuff = KeywordShopRepository.getKeywordStuff(currentItem.itemMeta.displayName
                        ?: currentItem.type.toString())
                if (stuff == null) {
                    playerInfo.sendErrorMessage(ShopMessage.CAN_NOT_SELL_ITEM)
                    return
                }
                val amount = when (event.click) {
                    ClickType.LEFT -> 1
                    ClickType.SHIFT_LEFT -> currentItem.amount
                    else -> return
                }
                playerMoney.giveMoney(stuff.price * amount)
                playerMoney.save()
                currentItem.amount -= amount
                playerInfo.sendInfoMessage(ShopMessage.SELL_ITEM)
            }
            // Out Inventory
            else -> return
        }
        inventory.setItem(5, 8, createItemStack(Material.GOLD_INGOT, "&6보유금액", listOf("&f${playerMoney.money.toMoneyFormat()}")))
    }

    private fun loadStuff(inventory: Inventory, shop: Shop, page: Int) {
        val stuffList = shop.getStuffList(page)
        (0..35).forEach {
            inventory.setItem(it, null)
        }
        stuffList.forEachIndexed { index, stuff -> inventory.setItem(index, stuff.toItemStack()) }
    }

    @EventHandler
    fun onPlayerInteractEntityEvent(event: PlayerInteractEntityEvent) {
        val npcName = event.rightClicked.name ?: return
        val shop = ShopRepository.getShopList().find { it.npcName == npcName } ?: return
        ShopInventory(event.player, shop).open()
    }

}