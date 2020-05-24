package com.namu.core.utility.itemdb.util

import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.kkomi.devlibrary.extension.setDisplay
import com.kkomi.devlibrary.extension.setLore
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.rpg.enhance.model.EnhanceItemMeta
import com.namu.core.utility.itemdb.model.entity.CustomItem
import com.namu.core.utility.itemdb.model.entity.StatOption
import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.inventory.ItemStack

fun ItemStack.refreshEquipmentItemLore() {

    val customItem = getNBTTagCompound(CustomItem::class.java) ?: return
    val totalOptions = mutableMapOf<StatType, Pair<Int, Int>>()
    val lastedLore = mutableListOf<String>()

    val equipmentOption = customItem.equipmentOption

    if (equipmentOption != null) {

        // level
        lastedLore.addAll(listOf(
                "< 장비아이템 - ${equipmentOption.equipType.korName} >",
                "",
                "&f레벨제한 : ${equipmentOption.levelLimit}"
        ))

        // stat
        equipmentOption.options.forEach { totalOptions[it.statOption] = it.value to 0 }

    }

    val enhanceItemMeta = getNBTTagCompound(EnhanceItemMeta::class.java)

    enhanceItemMeta?.scrollOptions?.forEach {
        val value = totalOptions[it.first]
        totalOptions[it.first] = if (value == null) {
            0 to it.second
        } else {
            value.first to value.second + it.second
        }
    }

    // stat
    if (totalOptions.isNotEmpty()) {
        lastedLore.addAll(listOf(
                "",
                *totalOptions.map { StatOption(it.key, it.value.first).toLoreStr(it.value.second) }.toTypedArray()
        ))
    }


    val itemMeta = itemMeta
    itemMeta?.lore = lastedLore.map { it.replaceChatColorCode() }
    setItemMeta(itemMeta)

    if (enhanceItemMeta != null && enhanceItemMeta.scrollLimit != 5) {
        setDisplay("${customItem.name} +${5 - enhanceItemMeta.scrollLimit}")
    }

}