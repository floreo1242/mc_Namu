package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.itemdb.model.entity.OtherItem
import java.io.File

class OtherItemFileDataSource(
        dataFolder: File,
        classType: Class<OtherItem>
) : ItemDataSource<OtherItem>, FileDataSource<OtherItem>(dataFolder, classType) {

    override fun getItem(code: String): OtherItem? {
        return getValue(code)
    }

    override fun getItemList(): List<OtherItem> {
        return getValueList()
    }

    override fun addItem(item: OtherItem) {
        setValue(item.code, item)
        saveFile(item.code, item)
    }

    override fun editItem(item: OtherItem) {
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