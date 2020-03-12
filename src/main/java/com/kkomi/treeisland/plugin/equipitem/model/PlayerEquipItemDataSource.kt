package com.kkomi.treeisland.plugin.equipitem.model

import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem

interface PlayerEquipItemDataSource {

    fun getPlayerEquipItem(uuid: String): PlayerEquipItem?

    fun getPlayerEquipItemList(): List<PlayerEquipItem>

    fun createPlayerEquipItem(playerEquipItem: PlayerEquipItem)

    fun editPlayerEquipItem(playerEquipItem: PlayerEquipItem)

    fun removePlayerEquipItem(uuid: String)

    fun reloadPlayerEquipItems()

    fun savePlayerEquipItem(playerEquipItem: PlayerEquipItem)

}