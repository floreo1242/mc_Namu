package com.kkomi.treeisland.library.extension

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun createItemStack(
        type: Material,
        display: String,
        lore: List<String>? = null,
        amount: Int = 1,
        durability: Short = 0
): ItemStack {
    val itemStack = ItemStack(type, amount)
    itemStack.itemMeta = itemStack.itemMeta.apply {
        this.displayName = display.replaceChatColorCode()
        lore?.let { this.lore = it.map { line -> line.replaceChatColorCode() } }
    }
    itemStack.durability = durability
    return itemStack
}

fun ItemStack.getSingle(): ItemStack {
    return clone().apply { amount = 1 }
}

fun ItemStack.setDisplay(display: String): ItemStack {
    val itemMeta = itemMeta
    itemMeta.displayName = display.replaceChatColorCode()
    setItemMeta(itemMeta)
    return this
}

fun ItemStack.getDisplay(): String? {
    return itemMeta.displayName
}

fun ItemStack.setLore(lore: List<String>): ItemStack {
    val itemMeta = itemMeta
    itemMeta.lore = lore.map { it.replaceChatColorCode() }
    setItemMeta(itemMeta)
    return this
}

fun ItemStack.addLore(lore: String): ItemStack {
    val itemMeta = itemMeta
    (itemMeta.lore ?: mutableListOf()).add(lore)
    setItemMeta(itemMeta)
    return this
}

fun ItemStack.getLore(): List<String>? {
    return itemMeta.lore
}

fun ItemStack.isAir() : Boolean {
    return type == Material.AIR
}