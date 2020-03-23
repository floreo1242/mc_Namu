package com.namu.core.rpg.role.model

import com.namu.core.rpg.role.model.entity.PlayerRole

interface PlayerRoleDataSource {

    fun getPlayerRole(uuid: String): PlayerRole?

    fun getPlayerRoleList(): List<PlayerRole>

    fun createPlayerRole(playerRole: PlayerRole)

    fun editPlayerRole(playerRole: PlayerRole)

    fun removePlayerRole(uuid: String)

    fun reloadPlayerRole()

    fun savePlayerRole(playerRole: PlayerRole)

}