package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import java.io.File

class EquipmentItemFileDataSource(
        dataFolder: File,
        classType: Class<EquipmentItem>
) : ItemDataSource<EquipmentItem>, FileDataSource<EquipmentItem>(dataFolder, classType) {

    override fun getItem(code: String): EquipmentItem? {
        return getValue(code)
    }

    override fun getItemList(): List<EquipmentItem> {
        return getValueList()
    }

    override fun createItem(item: EquipmentItem) {
        setValue(item.code, item)
    }

    override fun editItem(item: EquipmentItem) {
        setValue(item.code, item)
    }

    override fun removeItem(code: String) {
        removeItem(code)
        deleteFile(code)
    }

    override fun reloadItems() {
        loadFiles()
    }

    override fun saveItem(item: EquipmentItem) {
        saveFile(item.code, item)
    }
}