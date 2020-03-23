package com.namu.core.life.maker.listener

import com.kkomi.devlibrary.PageList
import com.kkomi.devlibrary.extension.count
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.itemdb.model.CustomItemRepository
import com.namu.core.life.maker.inventory.MakingInventory
import com.namu.core.life.maker.inventory.RecipeListInventory
import com.namu.core.life.maker.model.RecipeRepository
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.util.playerRecipe
import com.namu.core.utility.itemdb.model.entity.CustomItem
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class MakingInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view
        val inventoryInfo = inventory.getServerTitleInfo() ?: return

        if (inventoryInfo.first != MakingInventory.TITLE) {
            return
        }

        val player = event.whoClicked as Player
        val recipe = RecipeRepository.getRecipe(inventoryInfo.second)!!

        when (event.rawSlot) {
            0, 6 -> {
                event.isCancelled = true
            }
            7 -> {
                if ((1..5).mapNotNull {
                            val currentItem = inventory.getItem(it)
                            val customItem = currentItem?.getNBTTagCompound(CustomItem::class.java)
                            customItem?.code to currentItem?.amount
                        }.filter { it.first != null && it.second != null }.sortedBy { it.first } == recipe.materialList.map { it.itemCode to it.amount }.sortedBy { it.first }) {
                    inventory.close()
                    player.sendInfoMessage("아이템을 제작하였습니다.")
                    player.inventory.addItem(CustomItemRepository.getCustomItem(recipe.resultItemCode)?.toItemStack())
                } else {
                    inventory.close()
                    player.sendInfoMessage("재료가 알맞지 않습니다.")
                    (1..5).mapNotNull {
                        inventory.getItem(it)
                    }.forEach {
                        player.inventory.addItem(it)
                    }
                }
            }
            8 -> {
                inventory.close()
                (1..5).mapNotNull {
                    inventory.getItem(it)
                }.forEach {
                    player.inventory.addItem(it)
                }
            }
            else -> {

            }
        }
    }

}