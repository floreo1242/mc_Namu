package com.namu.core.economy.auction.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.count
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.economy.auction.inventory.AuctionInventory
import com.namu.core.economy.auction.model.AuctionRepository
import com.namu.core.economy.auction.model.entity.AuctionStuff
import com.namu.core.economy.money.model.entity.CheckPaper
import com.namu.core.economy.money.util.edit
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.utility.post.util.edit
import com.namu.core.utility.post.util.playerPostBox
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.InventoryView
import java.util.*

class AuctionInventoryListener : Listener {

    private val playerByPage = mutableMapOf<Player, Int>()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {

        val inventory = event.view

        if (inventory.title != AuctionInventory.TITLE) {
            return
        }

        val player = event.player as Player

        playerByPage[player] = 1

        loadPage(player, inventory)

    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view

        if (inventory.title != AuctionInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = event.whoClicked as Player
        val pageList = PageList(45, AuctionRepository.getAuction().auctionStuffList)

        when (event.rawSlot) {
            48 -> {
                if ((playerByPage[player] ?: 1) == 1) {
                    player.sendErrorMessage("처음 페이지 입니다.")
                    return
                }

                playerByPage.count(player, -1)
                loadPage(player, inventory)
            }
            50 -> {
                if ((playerByPage[player] ?: 1) == pageList.lastPageIndex) {
                    player.sendErrorMessage("마지막 페이지 입니다.")
                    return
                }

                playerByPage.count(player, 1)
                loadPage(player, inventory)
            }
            in 0..44 -> { // stuff array
                val currentItemStack = event.currentItem ?: return

                val auctionStuff = currentItemStack.getNBTTagCompound(AuctionStuff.Meta::class.java) ?: return

                println("${player.playerMoney.money} < ${auctionStuff.price}")
                if (player.playerMoney.money < auctionStuff.price) {
                    player.sendErrorMessage("소지금이 부족합니다.")
                    return
                }

                val stuff = AuctionRepository.getAuction().auctionStuffList.find { it.uuid == auctionStuff.uuid }?.item

                if (stuff == null) {
                    player.sendErrorMessage("이미 구매되었거나 존재하지 않는 아이템 입니다.")
                    return
                }

                // seller
                val seller = Bukkit.getOfflinePlayer(UUID.fromString(auctionStuff.ownerUuid))
                seller.playerPostBox?.apply { postItems.add(CheckPaper("경매장", (auctionStuff.price * 0.9).toInt()).toItemStack()) }?.edit()

                // buyer
                player.playerMoney.apply { money -= auctionStuff.price }.edit()
                player.playerPostBox.apply { postItems.add(stuff) }.edit()
                AuctionRepository.apply { removeAuctionStuff(auctionStuff.uuid) }.saveAuction()
                loadPage(player, inventory)

                if (seller.isOnline) {
                    seller.player?.sendInfoMessage("물건이 판매되었습니다.")
                }
                player.sendInfoMessage("물건이 구매되어 우편함으로 전송되었습니다.")
            }
            else -> {

            }
        }

    }

    private fun loadPage(player: Player, inventory: InventoryView) {
        val pageList = PageList(45, AuctionRepository.getAuction().auctionStuffList)

        (0 until 45).forEach {
            inventory.setItem(it, null)
        }

        pageList.getPage((playerByPage[player] ?: 1) - 1).forEach {
            inventory.topInventory.addItem(it.toItemStack())
        }
    }

}