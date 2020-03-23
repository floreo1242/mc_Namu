package com.namu.core.utility.post.model

import com.namu.core.MainCore
import com.namu.core.utility.post.model.entity.PlayerPostBox
import java.io.File

object PlayerPostBoxRepository {

    private val playerPostBoxDataSource = PlayerPostBoxFileDataSource(File(MainCore.postPlugin.dataFolder.parent + "/player"), PlayerPostBox::class.java)

    fun getPlayerPostBox(name: String): PlayerPostBox? {
        return playerPostBoxDataSource.getPlayerPostBox(name)
    }

    fun getPlayerPostBoxList(): List<PlayerPostBox> {
        return playerPostBoxDataSource.getPlayerPostBoxList()
    }

    fun createPlayerPostBox(playerPostBox: PlayerPostBox) {
        playerPostBoxDataSource.createPlayerPostBox(playerPostBox)
    }

    fun editPlayerPostBox(playerPostBox: PlayerPostBox) {
        playerPostBoxDataSource.editPlayerPostBox(playerPostBox)
    }

    fun savePlayerPostBox(playerPostBox: PlayerPostBox) {
        playerPostBoxDataSource.savePlayerPostBox(playerPostBox)
    }

    fun removePlayerPostBox(name: String) {
        playerPostBoxDataSource.removePlayerPostBox(name)
    }

    fun reloadPlayerPostBox() {
        playerPostBoxDataSource.reloadPlayerPostBox()
    }

}