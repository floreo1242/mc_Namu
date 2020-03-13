package com.kkomi.treeisland.plugin.role.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import java.io.File

object PlayerRoleRepository {

    private val playerRoleDataSource = PlayerRoleFileDataSource(File(Treeisland.rolePlugin.dataFolder.path + "/players"), PlayerRole::class.java)

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