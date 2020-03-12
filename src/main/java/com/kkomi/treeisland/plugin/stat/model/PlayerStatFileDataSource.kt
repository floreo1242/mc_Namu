package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import java.io.File

class PlayerStatFileDataSource(
        dataFolder: File,
        classType: Class<PlayerStat>
) : PlayerStatDataSource, FileDataSource<PlayerStat>(dataFolder, classType) {

    override fun getPlayerStat(uuid: String): PlayerStat? {
        return getValue(uuid)
    }

    override fun getPlayerStatList(): List<PlayerStat> {
        return getValueList()
    }

    override fun createPlayerStat(playerStat: PlayerStat) {
        setValue(playerStat.uuid, playerStat)
    }

    override fun editPlayerStat(playerStat: PlayerStat) {
        setValue(playerStat.uuid, playerStat)
    }

    override fun savePlayerStat(playerStat: PlayerStat) {
        saveFile(playerStat.uuid, playerStat)
    }

    override fun removePlayerStat(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun reloadPlayerStats() {
        loadFiles()
    }

}