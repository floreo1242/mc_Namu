package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.extension.replaceChatColorCode
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItemType
import org.bukkit.Material
import java.io.File

object ConsumptionItemRepository : ItemRepository<ConsumptionItem> {

    private val consumptionItemFileDataSource = ConsumptionItemFileDataSource(File("${Treeisland.itemDbPlugin.dataFolder}/consumption"), ConsumptionItem::class.java)

    override fun getItem(code: String): ConsumptionItem? {
        return consumptionItemFileDataSource.getItem(code)
    }

    override fun getItemFromItemDisplay(display: String): ConsumptionItem? {
        return consumptionItemFileDataSource.getItemList().find { it.name.replaceChatColorCode() == display }
    }

    override fun getItemList(): List<ConsumptionItem> {
        return consumptionItemFileDataSource.getItemList()
    }

    override fun createItem(code: String) {
        consumptionItemFileDataSource.addItem(
                ConsumptionItem(
                        code,
                        "Consumption Item Name",
                        "Consumption Item Description",
                        Material.POTION,
                        0,
                        0,
                        0,
                        5,
                        0,
                        ConsumptionItemType.HEALTH
                )
        )
    }

    override fun removeItem(code: String) {
        consumptionItemFileDataSource.removeItem(code)
    }

    override fun editItem(item: ConsumptionItem) {
        consumptionItemFileDataSource.editItem(item)
    }

    override fun reloadItems() {
        consumptionItemFileDataSource.reloadItems()
    }
}