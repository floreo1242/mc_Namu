package com.namu.core.life.maker.model

import com.namu.core.life.maker.model.entity.PlayerRecipe

interface PlayerRecipeDataSource {

    fun getPlayerRecipe(uuid: String): PlayerRecipe?

    fun getPlayerRecipeList(): List<PlayerRecipe>

    fun createPlayerRecipe(playerRecipe: PlayerRecipe)

    fun editPlayerRecipe(playerRecipe: PlayerRecipe)

    fun removePlayerRecipe(uuid: String)

    fun savePlayerRecipe(playerRecipe: PlayerRecipe)

    fun reloadPlayerRecipe()

}