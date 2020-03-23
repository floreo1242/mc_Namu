package com.namu.core.life.maker.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.life.maker.model.RecipeDataSource
import com.namu.core.life.maker.model.entity.Recipe
import java.io.File

class RecipeFileDataSource(
        dataFolder: File,
        classType: Class<Recipe>
) : RecipeDataSource, FileDataSource<Recipe>(dataFolder, classType) {

    override fun getRecipe(name: String): Recipe? {
        return getValue(name)
    }

    override fun getRecipeList(): List<Recipe> {
        return getValueList()
    }

    override fun createRecipe(recipe: Recipe) {
        setValue(recipe.name, recipe)
        saveFile(recipe.name, recipe)
        println("saveFile(recipe.name, recipe)")
    }

    override fun editRecipe(recipe: Recipe) {
        setValue(recipe.name, recipe)
        saveFile(recipe.name, recipe)
    }

    override fun removeRecipe(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun saveRecipe(recipe: Recipe) {
        saveFile(recipe.name, recipe)
    }

    override fun reloadRecipe() {
        loadFiles()
    }

}