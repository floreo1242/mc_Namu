package com.namu.core.life.maker.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.life.maker.model.PlayerRecipeDataSource
import com.namu.core.life.maker.model.entity.PlayerRecipe
import java.io.File

class PlayerRecipeFileDataSource(
        dataFolder: File,
        classType: Class<PlayerRecipe>
) : PlayerRecipeDataSource, FileDataSource<PlayerRecipe>(dataFolder, classType) {

    override fun getPlayerRecipe(uuid: String): PlayerRecipe? {
        return getValue(uuid)
    }

    override fun getPlayerRecipeList(): List<PlayerRecipe> {
        return getValueList()
    }

    override fun createPlayerRecipe(playerRecipe: PlayerRecipe) {
        setValue(playerRecipe.uuid, playerRecipe)
        saveFile(playerRecipe.uuid, playerRecipe)
    }

    override fun editPlayerRecipe(playerRecipe: PlayerRecipe) {
        setValue(playerRecipe.uuid, playerRecipe)
        saveFile(playerRecipe.uuid, playerRecipe)
    }

    override fun removePlayerRecipe(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }
    
    override fun savePlayerRecipe(playerRecipe : PlayerRecipe) {
        saveFile(playerRecipe.uuid, playerRecipe)
    }

    override fun reloadPlayerRecipe() {
        loadFiles()
    }

}