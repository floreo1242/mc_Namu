package com.namu.core.rpg.equip.model

import com.namu.core.MainCore
import com.namu.core.rpg.equip.model.entity.PlayerEquipInfo
import java.io.File

object PlayerEquipInfoRepository {

    private val playerEquipInfoDataSource = PlayerEquipInfoFileDataSource(File(MainCore.equipPlugin.dataFolder.path + "/"), PlayerEquipInfo::class.java)

    fun getPlayerEquipInfo(name: String): PlayerEquipInfo? {
        return playerEquipInfoDataSource.getPlayerEquipInfo(name)
    }

    fun getPlayerEquipInfoList(): List<PlayerEquipInfo> {
        return playerEquipInfoDataSource.getPlayerEquipInfoList()
    }

    fun createPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo) {
        playerEquipInfoDataSource.createPlayerEquipInfo(playerEquipInfo)
    }

    fun editPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo) {
        playerEquipInfoDataSource.editPlayerEquipInfo(playerEquipInfo)
    }

    fun savePlayerEquipInfo(playerEquipInfo: PlayerEquipInfo) {
        playerEquipInfoDataSource.savePlayerEquipInfo(playerEquipInfo)
    }

    fun removePlayerEquipInfo(name: String) {
        playerEquipInfoDataSource.removePlayerEquipInfo(name)
    }

    fun reloadPlayerEquipInfo() {
        playerEquipInfoDataSource.reloadPlayerEquipInfo()
    }

}