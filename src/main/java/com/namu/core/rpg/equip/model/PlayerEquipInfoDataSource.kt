package com.namu.core.rpg.equip.model

import com.namu.core.rpg.equip.model.entity.PlayerEquipInfo

interface PlayerEquipInfoDataSource {

    fun getPlayerEquipInfo(name: String): PlayerEquipInfo?

    fun getPlayerEquipInfoList(): List<PlayerEquipInfo>

    fun createPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo)

    fun editPlayerEquipInfo(playerEquipInfo: PlayerEquipInfo)

    fun removePlayerEquipInfo(name: String)

    fun savePlayerEquipInfo(playerEquipInfo: PlayerEquipInfo)

    fun reloadPlayerEquipInfo()

}