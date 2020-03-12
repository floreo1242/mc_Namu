package com.kkomi.treeisland.plugin.equipitem.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import java.io.File

class PlayerEquipItemFileDataSource(
        dataFolder: File,
        classType: Class<PlayerEquipItem>
) : PlayerEquipItemDataSource, FileDataSource<PlayerEquipItem>(dataFolder, classType) {

    override fun getPlayerEquipItem(uuid: String): PlayerEquipItem? {
        return getValue(uuid)
    }

    override fun getPlayerEquipItemList(): List<PlayerEquipItem> {
        return getValueList()
    }

    override fun createPlayerEquipItem(playerEquipItem: PlayerEquipItem) {
        setValue(playerEquipItem.uuid, playerEquipItem)
    }

    override fun editPlayerEquipItem(playerEquipItem: PlayerEquipItem) {
        setValue(playerEquipItem.uuid, playerEquipItem)
    }

    override fun removePlayerEquipItem(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun savePlayerEquipItem(playerEquipItem: PlayerEquipItem) {
        saveFile(playerEquipItem.uuid, playerEquipItem)
    }

    override fun reloadPlayerEquipItems() {
        loadFiles()
    }

}