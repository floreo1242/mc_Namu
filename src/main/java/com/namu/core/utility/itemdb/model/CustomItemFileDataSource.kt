package com.namu.core.utility.itemdb.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.utility.itemdb.model.entity.CustomItem
import java.io.File

class CustomItemFileDataSource(
        dataFolder: File,
        classType: Class<CustomItem>
) : CustomItemDataSource, FileDataSource<CustomItem>(dataFolder, classType) {

    override fun getCustomItem(name: String): CustomItem? {
        return getValue(name)
    }

    override fun getCustomItemList(): List<CustomItem> {
        return getValueList()
    }

    override fun createCustomItem(customItem: CustomItem) {
        setValue(customItem.code, customItem)
        saveFile(customItem.code, customItem)
    }

    override fun editCustomItem(customItem: CustomItem) {
        setValue(customItem.code, customItem)
        saveFile(customItem.code, customItem)
    }

    override fun removeCustomItem(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun saveCustomItem(customItem: CustomItem) {
        saveFile(customItem.code, customItem)
    }

    override fun reloadCustomItem() {
        loadFiles()
    }

}