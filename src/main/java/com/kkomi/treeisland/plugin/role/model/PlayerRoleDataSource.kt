package com.kkomi.treeisland.plugin.role.model

import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole

interface PlayerRoleDataSource {

    fun getPlayerRole(uuid: String): PlayerRole?

    fun getPlayerRoleList(): List<PlayerRole>

    fun addPlayerRole(playerRole: PlayerRole)

    fun editPlayerRole(playerRole: PlayerRole)

    fun removePlayerRole(uuid: String)

    fun reloadPlayerRole()

    fun savePlayerRole(playerRole: PlayerRole)

}