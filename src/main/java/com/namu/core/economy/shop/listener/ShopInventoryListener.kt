package com.namu.core.economy.shop.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.*
import com.kkomi.devlibrary.inventory.InventoryMessage
import com.namu.core.economy.money.model.PlayerMoneyRepository
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.economy.shop.inventory.ShopInventory
import com.namu.core.economy.shop.model.SellShopRepository
import com.namu.core.economy.shop.model.ShopMessage
import com.namu.core.economy.shop.model.ShopRepository
import com.namu.core.economy.shop.model.entity.Shop
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.InventoryView

class ShopInventoryListener : Listener {

    private val playerByPageIndex: MutableMap<Player, Int> = mutableMapOf()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {

        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != ShopInventory.TITLE) {
            return
        }

        val shop = ShopRepository.getShop(data.second)!!
        playerByPageIndex[event.player as Player] = 0
        loadStuff(inventory, shop, 0)

    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view
        val slot = event.rawSlot
        val player = event.whoClicked as Player
        val playerMoney = player.playerMoney
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != ShopInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val shop = ShopRepository.getShop(data.second)!!
        val currentItem = event.currentItem ?: return

        if (currentItem.type == Material.AIR) {
            return
        }

        val page = playerByPageIndex[player]!!
        val pageList = PageList(36, shop.stuffList.toList())

        when {
            slot == 48 -> {
                // 처음 페이지 일 경우
                if (page == 0) {
                    player.sendErrorMessage(InventoryMessage.PAGE_FIRST)
                    return
                }

                // 이전 페이지로 이동 후 아이템 로드
                playerByPageIndex.count(player, -1)
                loadStuff(inventory, shop, page)
            }
            slot == 50 -> {
                // 마지막 페이지 일 경우
                if (page == pageList.lastPageIndex) {
                    player.sendErrorMessage(InventoryMessage.PAGE_LAST)
                    return
                }

                // 다음 페이지로 이동 후 아이템 로드
                playerByPageIndex.count(player, 1)
                loadStuff(inventory, shop, page)
            }
            // Shop Area
            (0..35).contains(slot) -> {
                val stuff = pageList.getPage(page)[slot]

                // 소지금이 부족한 경우
                if (playerMoney.money < stuff.price) {
                    player.sendErrorMessage("소지금이 부족합니다.")
                    return
                }

                // 인벤토리 공간이 부족한 경우
                if (player.inventory.storageContents.filter { it == null }.count() == 0) {
                    player.sendErrorMessage(InventoryMessage.INSUFFICIENCY_SPACE)
                    return
                }

                // 구매처리
                PlayerMoneyRepository.editPlayerMoney(playerMoney.apply { takeMoney(stuff.price.toLong()) })
                player.inventory.addItem(stuff.itemStack)
                player.sendInfoMessage(ShopMessage.BUY_ITEM)
            }
            // Player Inventory Area
            (54..89).contains(slot) -> {
                val stuff = SellShopRepository.getSellStuff(currentItem)

                // 해당 아이템을 판매 할 수 없는 경우
                if (stuff == null) {
                    player.sendErrorMessage(ShopMessage.CAN_NOT_SELL_ITEM)
                    return
                }

                // 클릭 타입에 따른 아이템 갯수 설정
                val amount = when (event.click) {
                    ClickType.LEFT -> 1
                    ClickType.SHIFT_LEFT -> currentItem.amount
                    else -> return
                }

                // 판매처리
                PlayerMoneyRepository.editPlayerMoney(playerMoney.apply { giveMoney(stuff.price * amount.toLong()) })
                currentItem.amount -= amount
                player.sendInfoMessage(ShopMessage.SELL_ITEM.format(stuff.price.toMoneyFormat(), amount, (stuff.price * amount).toMoneyFormat()))
            }
            // Out Inventory
            else -> return
        }
        inventory.setItem(53, createItemStack(Material.GOLD_INGOT, "&6보유금액", listOf("&f${playerMoney.money.toMoneyFormat()}")))
    }

    private fun loadStuff(inventory: InventoryView, shop: Shop, page: Int) {
        val stuffList = PageList(36, shop.stuffList.toList()).getPage(page)
        (0..35).forEach { inventory.setItem(it, null) }
        stuffList.forEachIndexed { index, stuff -> inventory.setItem(index, stuff.toItemStack()) }
    }

    @EventHandler
    fun onPlayerInteractEntityEvent(event: PlayerInteractEntityEvent) {
        val npcName = event.rightClicked.name ?: return
        val shop = ShopRepository.getShopList().find { it.npcName == npcName } ?: return
        ShopInventory(event.player, shop).open()
    }

}