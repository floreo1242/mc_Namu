package com.kkomi.treeisland.plugin.itemdb.util

import com.kkomi.devlibrary.extension.setDisplay
import com.kkomi.devlibrary.extension.setLore
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.treeisland.plugin.enhance.model.EnhanceItemMeta
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItemOption
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.inventory.ItemStack

fun ItemStack.refreshEquipmentItemLore() {

    val equipmentItem = getNBTTagCompound(EquipmentItem::class.java) ?: return
    val enhanceItemMeta = getNBTTagCompound(EnhanceItemMeta::class.java)

    val totalOptions = mutableMapOf<StatOption, Pair<Int, Int>>()

    equipmentItem.baseOptions
            .forEach { totalOptions[it.statOption] = it.value to 0 }

    (enhanceItemMeta?.scrollOptions ?: mutableListOf())
            .forEach {
                val value = totalOptions[it.first]
                totalOptions[it.first] = if (value == null) {
                    0 to it.second
                } else {
                    value.first to value.second + it.second
                }
            }

    setLore(listOf(
            "",
            "&f착용직업 : ${equipmentItem.job}",
            "&f레벨제한 : ${equipmentItem.levelLimit}",
            "",
            *totalOptions.map { EquipmentItemOption(it.key, it.value.first).toLoreStr(it.value.second) }.toTypedArray()
    ))

    if (enhanceItemMeta != null && enhanceItemMeta.scrollLimit != 5) {
        setDisplay("${equipmentItem.name} +${5 - enhanceItemMeta.scrollLimit}")
    }


}