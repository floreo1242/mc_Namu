package com.kkomi.treeisland.plugin.post.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.post.model.entity.PlayerPostBox
import java.io.File

class PlayerPostBoxFileDataSource(
        dataFolder: File,
        classType: Class<PlayerPostBox>
) : PlayerPostBoxDataSource, FileDataSource<PlayerPostBox>(dataFolder, classType) {

    override fun getPlayerPostBox(name: String): PlayerPostBox? {
        return getValue(name)
    }

    override fun getPlayerPostBoxList(): List<PlayerPostBox> {
        return getValueList()
    }

    override fun createPlayerPostBox(playerPostBox: PlayerPostBox) {
        setValue(playerPostBox.uuid, playerPostBox)
    }

    override fun editPlayerPostBox(playerPostBox: PlayerPostBox) {
        setValue(playerPostBox.uuid, playerPostBox)
    }

    override fun removePlayerPostBox(name: String) {
        removeValue(name)
        deleteFile(name)
    }
    
    override fun savePlayerPostBox(playerPostBox : PlayerPostBox) {
        saveFile(playerPostBox.uuid, playerPostBox)
    }

    override fun reloadPlayerPostBox() {
        loadFiles()
    }

}