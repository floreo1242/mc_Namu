package com.kkomi.post.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.post.model.PlayerPostBoxFileDataSource
import com.kkomi.treeisland.plugin.post.model.entity.PlayerPostBox
import java.io.File

object PlayerPostBoxRepository {

    private val playerPostBoxDataSource = PlayerPostBoxFileDataSource(File(Treeisland.postPlugin.dataFolder.parent + "/player"), PlayerPostBox::class.java)

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