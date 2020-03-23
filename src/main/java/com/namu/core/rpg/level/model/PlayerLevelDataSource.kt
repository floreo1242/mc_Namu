package com.namu.core.rpg.level.model

import com.namu.core.rpg.level.model.entity.PlayerLevel

interface PlayerLevelDataSource {

    fun getPlayerLevel(uuid: String): PlayerLevel?

    fun createPlayerLevel(playerLevel: PlayerLevel)

    fun editPlayerLevel(playerLevel: PlayerLevel)

    fun removePlayerLevel(uuid: String)

    fun reloadPlayerLevel()

    fun savePlayerLevel(playerLevel: PlayerLevel)

    fun getPlayerLevelList(): List<PlayerLevel>

}