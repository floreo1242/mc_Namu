package com.namu.core.rpg.stat.model

import com.namu.core.rpg.stat.model.entity.PlayerStat

interface PlayerStatDataSource {

    fun getPlayerStat(uuid: String): PlayerStat?

    fun getPlayerStatList(): List<PlayerStat>

    fun createPlayerStat(playerStat: PlayerStat)

    fun editPlayerStat(playerStat: PlayerStat)

    fun removePlayerStat(uuid: String)

    fun reloadPlayerStats()

    fun savePlayerStat(playerStat: PlayerStat)

}