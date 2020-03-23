package com.namu.core.life.maker.util

import com.namu.core.life.maker.model.PlayerRecipeRepository
import com.namu.core.life.maker.model.entity.PlayerRecipe
import com.namu.core.utility.post.model.PlayerPostBoxRepository
import com.namu.core.utility.post.model.entity.PlayerPostBox
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerRecipe: PlayerRecipe
    get() {
        val playerRecipe = PlayerRecipeRepository.getPlayerRecipe(uniqueId.toString())
        if (playerRecipe == null) {
            val createdPlayerRecipe = PlayerRecipe(mutableListOf(), uniqueId.toString())
            PlayerRecipeRepository.createPlayerRecipe(createdPlayerRecipe)
            return createdPlayerRecipe
        }
        return playerRecipe
    }

val OfflinePlayer.playerRecipe: PlayerRecipe?
    get() {
        return PlayerRecipeRepository.getPlayerRecipe(uniqueId.toString())
    }

fun PlayerRecipe.edit() {
    PlayerRecipeRepository.editPlayerRecipe(this)
    PlayerRecipeRepository.savePlayerRecipe(this)
}