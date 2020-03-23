package com.namu.core.life.maker.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.count
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.economy.auction.inventory.AuctionInventory
import com.namu.core.economy.auction.model.AuctionRepository
import com.namu.core.economy.auction.model.entity.AuctionStuff
import com.namu.core.economy.money.model.entity.CheckPaper
import com.namu.core.economy.money.util.edit
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.life.maker.inventory.MakingInventory
import com.namu.core.life.maker.inventory.RecipeListInventory
import com.namu.core.life.maker.model.PlayerRecipeRepository
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.MakerCategory
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.util.playerRecipe
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

class RecipeListInventoryListener : Listener {

    private val playerByPage = mutableMapOf<Player, Int>()

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {

        val inventory = event.view
        val inventoryInfo = inventory.getServerTitleInfo() ?: return

        if (inventoryInfo.first != RecipeListInventory.TITLE) {
            return
        }

        val player = event.player as Player

        playerByPage[player] = 1

        loadPage(player, inventory, inventoryInfo.second)

    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view
        val inventoryInfo = inventory.getServerTitleInfo() ?: return

        if (inventoryInfo.first != RecipeListInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = event.whoClicked as Player
        val pageList = PageList(45, player.playerRecipe.recipeList)

        when (event.rawSlot) {
            48 -> {
                if ((playerByPage[player] ?: 1) == 1) {
                    player.sendErrorMessage("처음 페이지 입니다.")
                    return
                }

                playerByPage.count(player, -1)
                loadPage(player, inventory, inventoryInfo.second)
            }
            50 -> {
                println(pageList.lastPageIndex)
                if ((playerByPage[player] ?: 1) == pageList.lastPageIndex) {
                    player.sendErrorMessage("마지막 페이지 입니다.")
                    return
                }

                playerByPage.count(player, 1)
                loadPage(player, inventory, inventoryInfo.second)
            }
            in 0..44 -> { // stuff array
                val currentItemStack = event.currentItem ?: return
                val recipe = currentItemStack.getNBTTagCompound(Recipe::class.java) ?: return
                MakingInventory(player, recipe).open()
            }
            else -> {

            }
        }

    }

    private fun loadPage(player: Player, inventory: InventoryView, category: String) {
        val pageList = PageList(45, player.playerRecipe.recipeList
                .mapNotNull { RecipeRepository.getRecipe(it) }
                .filter { it.categoryName == category })

        (0 until 45).forEach {
            inventory.setItem(it, null)
        }

        pageList.getPage((playerByPage[player] ?: 1) - 1).forEach {
            inventory.topInventory.addItem(it.toItemStack())
        }
    }

}