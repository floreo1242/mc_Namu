package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import java.io.File

object PlayerStatRepository {

    private val playerStatFileDataSource = PlayerStatFileDataSource(File("${Treeisland.statPlugin.dataFolder}/players"), PlayerStat::class.java)

    fun getPlayerStat(uuid: String): PlayerStat? {
        return playerStatFileDataSource.getPlayerStat(uuid)
    }

    fun getPlayerStatList(): List<PlayerStat> {
        return playerStatFileDataSource.getPlayerStatList()
    }

    fun createPlayerStat(uuid: String) {
        playerStatFileDataSource.createPlayerStat(
                PlayerStat(
                        uuid,
                        mutableMapOf(
                                StatOption.STRENGTH to 0,
                                StatOption.INTELLIGENCE to 0,
                                StatOption.DEXTERITY to 0,
                                StatOption.DEFENSE to 0,
                                StatOption.AGILITY to 0
                        ),
                        0,
                        statOptionToMap()
                )
        )
    }

    fun editPlayerStat(playerStat: PlayerStat) {
        playerStatFileDataSource.editPlayerStat(playerStat)
    }

    fun removePlayerStat(uuid: String) {
        playerStatFileDataSource.removePlayerStat(uuid)
    }

    fun reloadPlayerStat() {
        playerStatFileDataSource.reloadPlayerStats()
    }

    private fun statOptionToMap(): MutableMap<StatOption, Int> {
        val map = mutableMapOf<StatOption, Int>()
        StatOption.values().forEach { map[it] = 0 }
        return map
    }

}