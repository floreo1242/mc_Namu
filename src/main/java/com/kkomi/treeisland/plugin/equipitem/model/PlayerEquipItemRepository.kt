package com.kkomi.treeisland.plugin.equipitem.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import java.io.File

object PlayerEquipItemRepository {

    private val playerEquipItemFileDataSource = PlayerEquipItemFileDataSource(File("${Treeisland.equipItemPlugin.dataFolder}/players"), PlayerEquipItem::class.java)

    fun getPlayerEquipItem(uuid: String): PlayerEquipItem? {
        return playerEquipItemFileDataSource.getPlayerEquipItem(uuid)
    }

    fun getPlayerEquipItemList(): List<PlayerEquipItem> {
        return playerEquipItemFileDataSource.getPlayerEquipItemList()
    }

    fun createPlayerEquipItem(uuid: String) {
        playerEquipItemFileDataSource.createPlayerEquipItem(PlayerEquipItem(uuid))
    }

    fun editPlayerEquipItem(playerEquipItem: PlayerEquipItem) {
        playerEquipItemFileDataSource.editPlayerEquipItem(playerEquipItem)
    }

    fun savePlayerEquipItem(playerEquipItem: PlayerEquipItem) {
        playerEquipItemFileDataSource.savePlayerEquipItem(playerEquipItem)
    }

    fun removePlayerEquipItem(uuid: String) {
        playerEquipItemFileDataSource.removePlayerEquipItem(uuid)
    }

    fun reloadPlayerEquipItem() {
        playerEquipItemFileDataSource.reloadPlayerEquipItems()
    }

}