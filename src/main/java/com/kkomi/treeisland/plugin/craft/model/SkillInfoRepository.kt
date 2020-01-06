package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.craft.model.CraftRecipeFileDataSource
import com.kkomi.treeisland.plugin.craft.model.entity.CraftRecipe
import com.kkomi.treeisland.plugin.skill.model.entity.CraftRecipe
import java.io.File

object CraftRecipeRepository {

    private val craftRecipeDataSource = CraftRecipeFileDataSource(File(Treeisland.skillPlugin.dataFolder.path + "/skills"), CraftRecipe::class.java)

    fun getCraftRecipe(name: String): CraftRecipe? {
        return craftRecipeDataSource.getCraftRecipe(name)
    }

    fun getCraftRecipeList(): List<CraftRecipe> {
        return craftRecipeDataSource.getCraftRecipeList()
    }

    fun addCraftRecipe(CraftRecipe: CraftRecipe) {
        craftRecipeDataSource.addCraftRecipe(CraftRecipe)
    }

    fun editCraftRecipe(CraftRecipe: CraftRecipe) {
        craftRecipeDataSource.editCraftRecipe(CraftRecipe)
    }

    fun removeCraftRecipe(name: String) {
        craftRecipeDataSource.removeCraftRecipe(name)
    }

    fun reloadCraftRecipe() {
        craftRecipeDataSource.reloadCraftRecipe()
    }

}