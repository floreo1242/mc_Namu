package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.OtherItem
import org.bukkit.Material
import java.io.File

object OtherItemRepository : ItemRepository<OtherItem> {

    private val otherItemFileDataSource = OtherItemFileDataSource(File("${Treeisland.itemDbPlugin.dataFolder}/other"), OtherItem::class.java)

    override fun getItem(code: String): OtherItem? {
        return otherItemFileDataSource.getItem(code)
    }

    override fun getItemFromItemDisplay(display: String): OtherItem? {
        return otherItemFileDataSource.getItemList().find { it.name.replaceChatColorCode() == display }
    }

    override fun getItemList(): List<OtherItem> {
        return otherItemFileDataSource.getItemList()
    }

    override fun createItem(code: String) {
        otherItemFileDataSource.addItem(
                OtherItem(
                        code,
                        "Other Item Name",
                        "Other Item Description",
                        Material.PAPER,
                        0
                )
        )
    }

    override fun saveItem(item: OtherItem) {
        otherItemFileDataSource.saveItem(item)
    }

    override fun removeItem(code: String) {
        otherItemFileDataSource.removeItem(code)
    }

    override fun editItem(item: OtherItem) {
        otherItemFileDataSource.editItem(item)
    }

    override fun reloadItems() {
        otherItemFileDataSource.reloadItems()
    }
}