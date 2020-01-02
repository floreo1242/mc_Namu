package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat

interface PlayerStatDataSource {

    fun getPlayerStat(uuid: String): PlayerStat?

    fun getPlayerStatList(): List<PlayerStat>

    fun createPlayerStat(playerStat: PlayerStat)

    fun editPlayerStat(playerStat: PlayerStat)

    fun removePlayerStat(uuid: String)

    fun reloadPlayerStats()

}