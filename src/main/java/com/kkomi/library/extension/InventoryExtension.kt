package com.kkomi.library.extension

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

fun Inventory.setItem(row: Int, col: Int, itemStack: ItemStack) {
    setItem(row * 9 + col, itemStack)
}
