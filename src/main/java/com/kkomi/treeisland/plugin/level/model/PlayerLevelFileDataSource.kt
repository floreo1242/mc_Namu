package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import java.io.File

class PlayerLevelFileDataSource(
        dataFolder: File,
        classType: Class<PlayerLevel>
) : PlayerLevelDataSource, FileDataSource<PlayerLevel>(dataFolder, classType) {

    override fun getPlayerLevel(uuid: String): PlayerLevel? {
        return getValue(uuid)
    }

    override fun addPlayerLevel(playerLevel: PlayerLevel) {
        setValue(playerLevel.uuid, playerLevel)
        saveFile(playerLevel.uuid, playerLevel)
    }

    override fun editPlayerLevel(playerLevel: PlayerLevel) {
        setValue(playerLevel.uuid, playerLevel)
        saveFile(playerLevel.uuid, playerLevel)
    }

    override fun removePlayerLevel(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun reloadPlayerLevel() {
        loadFiles()
    }

}