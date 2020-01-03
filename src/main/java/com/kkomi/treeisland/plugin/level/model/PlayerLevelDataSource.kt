package com.kkomi.treeisland.plugin.level.model

import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel

interface PlayerLevelDataSource {

    fun getPlayerLevel(uuid: String): PlayerLevel?

    fun addPlayerLevel(playerLevel: PlayerLevel)

    fun editPlayerLevel(playerLevel: PlayerLevel)

    fun removePlayerLevel(uuid: String)

    fun reloadPlayerLevel()

}