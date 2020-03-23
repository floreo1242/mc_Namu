package com.namu.core.rpg.stat.model

import com.namu.core.MainCore
import com.namu.core.utility.itemdb.model.entity.StatType
import com.namu.core.rpg.stat.model.entity.PlayerStat
import java.io.File

object PlayerStatRepository {

    private val playerStatFileDataSource = PlayerStatFileDataSource(File("${MainCore.statPlugin.dataFolder}/players"), PlayerStat::class.java)

    fun getPlayerStat(uuid: String): PlayerStat? {
        return playerStatFileDataSource.getPlayerStat(uuid)
    }

    fun getPlayerStatList(): List<PlayerStat> {
        return playerStatFileDataSource.getPlayerStatList()
    }

    fun createPlayerStat(playerStat: PlayerStat) {
        playerStatFileDataSource.createPlayerStat(playerStat)
    }

    fun editPlayerStat(playerStat: PlayerStat) {
        playerStatFileDataSource.editPlayerStat(playerStat)
    }

    fun savePlayerStat(playerStat: PlayerStat) {
        playerStatFileDataSource.savePlayerStat(playerStat)
    }

    fun removePlayerStat(uuid: String) {
        playerStatFileDataSource.removePlayerStat(uuid)
    }

    fun reloadPlayerStat() {
        playerStatFileDataSource.reloadPlayerStats()
    }

    private fun statOptionToMap(): MutableMap<StatType, Int> {
        val map = mutableMapOf<StatType, Int>()
        StatType.values().forEach { map[it] = 0 }
        return map
    }

}