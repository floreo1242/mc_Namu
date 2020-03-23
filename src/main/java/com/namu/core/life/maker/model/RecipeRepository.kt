package com.namu.core.life.maker.model

import com.namu.core.MainCore
import com.namu.core.life.maker.model.entity.Recipe
import java.io.File

object RecipeRepository {

    private val recipeDataSource = RecipeFileDataSource(File(MainCore.makerPlugin.dataFolder.path + "/recipe"), Recipe::class.java)

    fun getRecipe(name: String): Recipe? {
        return recipeDataSource.getRecipe(name)
    }

    fun getRecipeList(): List<Recipe> {
        return recipeDataSource.getRecipeList()
    }

    fun createRecipe(recipe: Recipe) {
        recipeDataSource.createRecipe(recipe)
    }

    fun editRecipe(recipe: Recipe) {
        recipeDataSource.editRecipe(recipe)
    }
    
    fun saveRecipe(recipe: Recipe) {
        recipeDataSource.saveRecipe(recipe)
    }            

    fun removeRecipe(name: String) {
        recipeDataSource.removeRecipe(name)
    }

    fun reloadRecipe() {
        recipeDataSource.reloadRecipe()
    }

}