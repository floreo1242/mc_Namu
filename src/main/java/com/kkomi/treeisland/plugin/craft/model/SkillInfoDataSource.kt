package com.kkomi.treeisland.plugin.craft.model

import com.kkomi.treeisland.plugin.craft.model.entity.CraftRecipe

interface CraftRecipeDataSource {

    fun getCraftRecipe(name: String): CraftRecipe?

    fun getCraftRecipeList(): List<CraftRecipe>

    fun addCraftRecipe(CraftRecipe: CraftRecipe)

    fun editCraftRecipe(CraftRecipe: CraftRecipe)

    fun removeCraftRecipe(name: String)

    fun reloadCraftRecipe()

}