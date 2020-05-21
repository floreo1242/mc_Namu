package com.namu.core.rpg.equip.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.equip.model.entity.PlayerEquipInfo
import java.io.File

class PlayerEquipInfoFileDataSource(
        dataFolder: File,
        classType: Class<PlayerEquipInfo>
) : PlayerEquipInfoDataSource, FileDataSource<PlayerEquipInfo>(dataFolder, classType) {

    override fun getPlayerEquipInfo(name: String): PlayerEquipInfo? {
        return getValue(name)
    }

    override fun getPlayerEquipInfoList(): List<PlayerEquipInfo> {
        return getValueList()
    }

    override fun createPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo) {
        setValue(playerEquipInfo.uuid, playerEquipInfo)
    }

    override fun editPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo) {
        setValue(playerEquipInfo.uuid, playerEquipInfo)
    }

    override fun removePlayerEquipInfo(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun savePlayerEquipInfo(playerEquipInfo : PlayerEquipInfo) {
        saveFile(playerEquipInfo.uuid, playerEquipInfo)
    }

    override fun reloadPlayerEquipInfo() {
        loadFiles()
    }

}