package com.namu.core.rpg.role.model

import com.namu.core.MainCore
import com.namu.core.rpg.role.model.entity.PlayerRole
import java.io.File

object PlayerRoleRepository {

    private val playerRoleDataSource = PlayerRoleFileDataSource(File(MainCore.rolePlugin.dataFolder.path + "/players"), PlayerRole::class.java)

    fun getPlayerRole(uuid: String): PlayerRole? {
        return playerRoleDataSource.getPlayerRole(uuid)
    }

    fun getPlayerRoleList(): List<PlayerRole> {
        return playerRoleDataSource.getPlayerRoleList()
    }

    fun createPlayerRole(playerRole: PlayerRole) {
        playerRoleDataSource.createPlayerRole(playerRole)
    }

    fun editPlayerRole(playerRole: PlayerRole) {
        playerRoleDataSource.editPlayerRole(playerRole)
    }

    fun savePlayerRole(playerRole: PlayerRole) {
        playerRoleDataSource.savePlayerRole(playerRole)
    }

    fun removePlayerRole(uuid: String) {
        playerRoleDataSource.removePlayerRole(uuid)
    }

    fun reloadPlayerRole() {
        playerRoleDataSource.reloadPlayerRole()
    }

}