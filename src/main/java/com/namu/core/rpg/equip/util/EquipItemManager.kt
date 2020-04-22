package com.namu.core.rpg.equip.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*


enum class EquipItemManager(val itemStack: ItemStack){
    BROWNGLASS(buildItem("", Material.BROWN_STAINED_GLASS_PANE, Arrays.asList(""))),
    GREENGLASS(buildItem("", Material.GREEN_STAINED_GLASS_PANE, Arrays.asList(""))),
    DIAMOND_HELMET(ItemStack(Material.DIAMOND_HELMET)),
    DIAMOND_CHESTPLATE(ItemStack(Material.DIAMOND_CHESTPLATE)),
    DIAMOND_LEGGINGS(ItemStack(Material.DIAMOND_LEGGINGS)),
    DIAMOND_BOOTS(ItemStack(Material.DIAMOND_BOOTS))
}

fun buildItem(display : String, type : Material, amount: Int, lore : MutableList<String>) : ItemStack {
    val stack = ItemStack(type, amount)
    val meta = stack.itemMeta
    meta.setDisplayName(display)
    meta.lore = lore;
    stack.setItemMeta(meta)
    return stack;
}

fun buildItem(display : String, type : Material, lore : MutableList<String>) : ItemStack {
    return buildItem(display, type, 1, lore);
}