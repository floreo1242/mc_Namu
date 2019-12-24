package com.kkomi.treeisland.library.extension

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.io.File


fun List<String>.toMap(separator: String): MutableMap<String, Int> {
    val resultMap = mutableMapOf<String, Int>()
    forEach { item ->
        val data = item.split(separator)
        resultMap[data[0]] = data[1].toInt()
    }
    return resultMap
}

fun List<ItemStack>.toMap(): Map<ItemStack, Int> {
    val inventoryMap = mutableMapOf<ItemStack, Int>()

    filterNotNull()
            .filter { it.type != Material.AIR }
            .forEach {
                val itemStack = it.getSingle()
                val amount = it.amount
                inventoryMap[itemStack] = (inventoryMap[itemStack] ?: 0) + amount
            }

    return inventoryMap
}

fun File.getYmlFileList(): List<File> {
    mkdirs()
    val filesList = listFiles { _, name -> name.endsWith(".yml") } ?: return listOf()
    return filesList.toList()
}