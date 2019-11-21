package com.kkomi.library.extension

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun createItemStack(
        type: Material,
        display: String,
        lore: List<String>? = null,
        amount: Int = 1
): ItemStack {
    val itemStack = ItemStack(type, amount)
    itemStack.itemMeta = itemStack.itemMeta.apply {
        this.displayName = display.replaceChatColorCode()
        lore?.let { this.lore = it.map { line -> line.replaceChatColorCode() } }
    }
    return itemStack
}