package com.kkomi.treeisland.library.extension

import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

fun Inventory.setItem(row: Int, col: Int, itemStack: ItemStack) {
    setItem(row * 9 + col, itemStack)
}

private val serverInventoryPrefixList = listOf(
        "스크립트 대화"
)

fun Inventory.getServerTitleInfo(): Pair<String, String>? {
    val temp = title.split(" - ")

    if (temp.size != 2) {
        return null
    }

    if (!serverInventoryPrefixList.contains(temp[0])) {
        return null
    }

    return temp[0] to temp[1]
}