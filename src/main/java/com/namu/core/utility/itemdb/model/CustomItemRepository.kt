package com.namu.core.utility.itemdb.model

import com.namu.core.utility.itemdb.model.entity.CustomItem
import com.namu.core.MainCore

object CustomItemRepository {

    private val customItemDataSource = CustomItemFileDataSource(MainCore.itemDbPlugin.dataFolder, CustomItem::class.java)

    fun getCustomItem(name: String): CustomItem? {
        return customItemDataSource.getCustomItem(name)
    }

    fun getCustomItemList(): List<CustomItem> {
        return customItemDataSource.getCustomItemList()
    }

    fun createCustomItem(customItem: CustomItem) {
        customItemDataSource.createCustomItem(customItem)
    }

    fun editCustomItem(customItem: CustomItem) {
        customItemDataSource.editCustomItem(customItem)
    }
    
    fun saveCustomItem(customItem: CustomItem) {
        customItemDataSource.saveCustomItem(customItem)
    }            

    fun removeCustomItem(name: String) {
        customItemDataSource.removeCustomItem(name)
    }

    fun reloadCustomItem() {
        customItemDataSource.reloadCustomItem()
    }

}