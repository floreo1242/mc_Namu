package com.namu.core.life.maker.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.life.maker.model.PlayerRecipeRepository
import com.namu.core.life.maker.model.entity.PlayerRecipe
import com.namu.core.life.maker.model.entity.Recipe
import com.namu.core.life.maker.util.edit
import com.namu.core.life.maker.util.playerRecipe
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerRecipeListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val playerRecipe = PlayerRecipeRepository.getPlayerRecipe(event.player.uniqueId.toString())
        if (playerRecipe == null) {
            PlayerRecipeRepository.createPlayerRecipe(PlayerRecipe(mutableListOf(), event.player.uniqueId.toString()))
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {

        println(event.player.inventory.itemInMainHand)

        val player = event.player
        val currentItem = player.inventory.itemInMainHand
        val recipe = currentItem.getNBTTagCompound(Recipe::class.java) ?: return

        if (player.playerRecipe.recipeList.contains(recipe.name)) {
            player.sendErrorMessage("이미 소지하고 있는 레시피 입니다.")
            return
        }

        player.playerRecipe.apply { recipeList.add(recipe.name) }.edit()
        currentItem.amount -= 1
        player.sendInfoMessage("레시피를 습득하였습니다.")

    }
}