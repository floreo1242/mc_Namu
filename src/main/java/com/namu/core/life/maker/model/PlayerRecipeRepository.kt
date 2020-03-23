package com.namu.core.life.maker.model

import com.namu.core.MainCore
import com.namu.core.life.maker.model.entity.PlayerRecipe
import java.io.File

object PlayerRecipeRepository {

    private val playerRecipeDataSource = PlayerRecipeFileDataSource(File(MainCore.makerPlugin.dataFolder.path + "/player"), PlayerRecipe::class.java)

    fun getPlayerRecipe(name: String): PlayerRecipe? {
        return playerRecipeDataSource.getPlayerRecipe(name)
    }

    fun getPlayerRecipeList(): List<PlayerRecipe> {
        return playerRecipeDataSource.getPlayerRecipeList()
    }

    fun createPlayerRecipe(playerRecipe: PlayerRecipe) {
        playerRecipeDataSource.createPlayerRecipe(playerRecipe)
    }

    fun editPlayerRecipe(playerRecipe: PlayerRecipe) {
        playerRecipeDataSource.editPlayerRecipe(playerRecipe)
    }

    fun savePlayerRecipe(playerRecipe: PlayerRecipe) {
        playerRecipeDataSource.savePlayerRecipe(playerRecipe)
    }

    fun removePlayerRecipe(name: String) {
        playerRecipeDataSource.removePlayerRecipe(name)
    }

    fun reloadPlayerRecipe() {
        playerRecipeDataSource.reloadPlayerRecipe()
    }

}