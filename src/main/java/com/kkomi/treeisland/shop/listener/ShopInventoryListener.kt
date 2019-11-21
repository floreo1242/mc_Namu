package com.kkomi.treeisland.shop.listener

import com.kkomi.library.extension.*
import com.kkomi.treeisland.integration.PlayerInfo
import com.kkomi.treeisland.shop.ShopPlugin
import com.kkomi.treeisland.shop.eneity.Shop
import com.kkomi.treeisland.shop.inventory.ShopInventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ShopInventoryListener : Listener {
    private val shopPageHash: MutableMap<Player, Int> = mutableMapOf()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {

        val inventory = event.inventory

        if (!inventory.title.contains("상점")) {
            return
        }

        shopPageHash[event.player as Player] = 1

        val shop = ShopPlugin.shopManager.getShop(inventory.title.split("-")[0])!!
        loadStuff(inventory, shop, 1)

    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val slot = event.rawSlot
        val inventory = event.inventory
        val playerInfo = PlayerInfo(event.whoClicked as Player)

        if (!inventory.title.contains("상점")) {
            return
        }

        event.isCancelled = true
        val playerMoney = playerInfo.moneyInfo
        val shop = ShopPlugin.shopManager.getShop(inventory.title.split("-")[0])!!
        val currentItem = if (event.currentItem == null || event.currentItem.type == Material.AIR) return else event.currentItem
        val page = shopPageHash[playerInfo.player]!!

        when {
            slot == 48 -> {
                if (page == 1) {
                    playerInfo.sendErrorMessage("처음 페이지 입니다.")
                    return
                }
                shopPageHash[playerInfo.player] = shopPageHash[playerInfo.player]?.minus(1) ?: 1
                loadStuff(inventory, shop, page)
            }
            slot == 50 -> {
                if (page == shop.getLastPageNum()) {
                    playerInfo.sendErrorMessage("마지막 페이지 입니다.")
                    return
                }
                shopPageHash[playerInfo.player] = shopPageHash[playerInfo.player]?.plus(1) ?: 1
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
                    playerInfo.sendErrorMessage("물건을 구매하려면 최소 1칸의 여유공간이 있어야 합니다.")
                    return
                }
                playerMoney.takeMoney(stuff.price)
                playerMoney.save()
                playerInfo.player.inventory.addItem(stuff.itemStack)
                playerInfo.sendInfoMessage("아이템을 구매하였습니다.")
            }
            // Player Inventory Area
            (54..89).contains(slot) -> {
                val stuff = ShopPlugin.shopManager.keywordShop.stuffList.find {
                    (currentItem.itemMeta.displayName
                            ?: currentItem.type.toString()).toUpperCase().contains(it.keyword.toUpperCase())
                }
                if (stuff == null) {
                    playerInfo.sendErrorMessage("판매할 수 없는 아이템 입니다.")
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
                playerInfo.sendInfoMessage("아이템을 판매하였습니다.")
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
        val shop = ShopPlugin.shopManager.ShopList.find { it.npcName == npcName } ?: return
        ShopInventory(event.player, shop).open()
    }

}