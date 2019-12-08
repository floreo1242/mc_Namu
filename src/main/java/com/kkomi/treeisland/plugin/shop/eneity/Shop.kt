package com.kkomi.treeisland.plugin.shop.eneity

import com.kkomi.treeisland.plugin.shop.ShopManager
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File

data class Shop(
        val manager: ShopManager,
        val name: String,
        val stuffList: MutableList<Stuff>,
        var npcName: String
) {
    constructor(manager: ShopManager, config: YamlConfiguration) : this(
            manager,
            config.getString("name"),
            config.getList("stuffList", mutableListOf<Stuff>()) as MutableList<Stuff>,
            config.getString("npcName")
    )

    fun getLastPageNum(): Int {
        if (stuffList.isEmpty()) return 1
        var page = stuffList.size / 36
        if (stuffList.size % 36 > 0) {
            page += 1
        }
        return page
    }

    fun getStuffList(page: Int): List<Stuff> {
        return if (stuffList.isEmpty()) {
            listOf()
        } else {
            if (page == getLastPageNum()) {
                stuffList.toTypedArray().copyOfRange((page - 1) * 36, stuffList.size).toList()
            } else {
                stuffList.toTypedArray().copyOfRange((page - 1) * 36, page * 36 - 1).toList()
            }
        }
    }

    fun addStuff(itemStack: ItemStack, price: Int) {
        stuffList.add(Stuff(itemStack, price))
    }

    fun removeStuff(index: Int) {
        stuffList.removeAt(index)
    }

    private val file: File
        get() {
            manager.folder.mkdir()
            return File(manager.folder, "$name${manager.EXT}")
        }

    fun save() {
        val config = YamlConfiguration.loadConfiguration(file)
        config.set("name", name)
        config.set("stuffList", stuffList)
        config.set("npcName", npcName)
        config.save(file)
    }

    fun remove() {
        manager.justRemove(name)
        file.delete()
    }
}