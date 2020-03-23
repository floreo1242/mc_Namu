package com.namu.core.rpg.role.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.role.model.entity.PlayerRole
import java.io.File

class PlayerRoleFileDataSource(
        dataFolder: File,
        classType: Class<PlayerRole>
) : PlayerRoleDataSource, FileDataSource<PlayerRole>(dataFolder, classType) {

    override fun getPlayerRole(uuid: String): PlayerRole? {
        return getValue(uuid)
    }

    override fun getPlayerRoleList(): List<PlayerRole> {
        return getValueList()
    }

    override fun createPlayerRole(playerRole: PlayerRole) {
        setValue(playerRole.uuid, playerRole)
        saveFile(playerRole.uuid, playerRole)
    }

    override fun editPlayerRole(playerRole: PlayerRole) {
        setValue(playerRole.uuid, playerRole)
        saveFile(playerRole.uuid, playerRole)
    }

    override fun savePlayerRole(playerRole: PlayerRole) {
        saveFile(playerRole.uuid, playerRole)
    }

    override fun removePlayerRole(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun reloadPlayerRole() {
        loadFiles()
    }

}