package com.kkomi.treeisland.plugin.craft.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.craft.model.entity.CraftRecipe
import java.io.File

class CraftRecipeFileDataSource(
        dataFolder: File,
        classType: Class<CraftRecipe>
) : CraftRecipeDataSource, FileDataSource<CraftRecipe>(dataFolder, classType) {

    override fun getCraftRecipe(name: String): CraftRecipe? {
        return getValue(name)
    }

    override fun getCraftRecipeList(): List<CraftRecipe> {
        return getValueList()
    }

    override fun addCraftRecipe(CraftRecipe: CraftRecipe) {
        setValue(CraftRecipe.name, CraftRecipe)
        saveFile(CraftRecipe.name, CraftRecipe)
    }

    override fun editCraftRecipe(CraftRecipe: CraftRecipe) {
        setValue(CraftRecipe.name, CraftRecipe)
        saveFile(CraftRecipe.name, CraftRecipe)
    }

    override fun removeCraftRecipe(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadCraftRecipe() {
        loadFiles()
    }

}