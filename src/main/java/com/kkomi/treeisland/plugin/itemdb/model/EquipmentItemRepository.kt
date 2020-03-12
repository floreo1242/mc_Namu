package com.kkomi.treeisland.plugin.itemdb.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.kkomi.treeisland.plugin.itemdb.model.entity.*
import org.bukkit.Material
import org.bukkit.entity.Player
import java.io.File

object EquipmentItemRepository : ItemRepository<EquipmentItem> {

    private val equipmentItemFileDataSource = EquipmentItemFileDataSource(File("${Treeisland.itemDbPlugin.dataFolder}/equipment"), EquipmentItem::class.java)

    override fun getItem(code: String): EquipmentItem? {
        return equipmentItemFileDataSource.getItem(code)
    }

    override fun getItemFromItemDisplay(display: String): EquipmentItem? {
        return equipmentItemFileDataSource.getItemList().find { it.name.replaceChatColorCode() == display }
    }

    override fun getItemList(): List<EquipmentItem> {
        return equipmentItemFileDataSource.getItemList()
    }

    override fun createItem(code: String) {
        equipmentItemFileDataSource.addItem(
                EquipmentItem(
                        code,
                        "Equipment Item Name",
                        EquipmentType.WEAPON,
                        Material.WOOD_SWORD,
                        0,
                        0,
                        "공용",
                        mutableListOf(
                                EquipmentItemOption(StatOption.MIN_DAMAGE, 10),
                                EquipmentItemOption(StatOption.MAX_DAMAGE, 10)
                        )
                )
        )
    }

    override fun removeItem(code: String) {
        equipmentItemFileDataSource.removeItem(code)
    }

    override fun editItem(item: EquipmentItem) {
        equipmentItemFileDataSource.editItem(item)
    }

    override fun reloadItems() {
        equipmentItemFileDataSource.reloadItems()
    }

    override fun saveItem(item: EquipmentItem) {
        equipmentItemFileDataSource.saveItem(item)
    }
}