package com.namu.core.life.maker.model

import com.namu.core.life.maker.model.entity.Recipe

interface RecipeDataSource {

    fun getRecipe(name: String): Recipe?

    fun getRecipeList(): List<Recipe>

    fun createRecipe(recipe: Recipe)

    fun editRecipe(recipe: Recipe)

    fun removeRecipe(name: String)

    fun saveRecipe(recipe: Recipe)

    fun reloadRecipe()

}