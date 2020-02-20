package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItem
import java.io.File

class ConsumptionItemFileDataSource(
        dataFolder: File,
        classType: Class<ConsumptionItem>
) : ItemDataSource<ConsumptionItem>, FileDataSource<ConsumptionItem>(dataFolder, classType) {

    override fun getItem(code: String): ConsumptionItem? {
        return getValue(code)
    }

    override fun getItemList(): List<ConsumptionItem> {
        return getValueList()
    }

    override fun addItem(item: ConsumptionItem) {
        setValue(item.code, item)
        saveFile(item.code, item)
    }

    override fun editItem(item: ConsumptionItem) {
        setValue(item.code, item)
        saveFile(item.code, item)
    }

    override fun removeItem(code: String) {
        removeItem(code)
        deleteFile(code)
    }

    override fun reloadItems() {
        loadFiles()
    }
}