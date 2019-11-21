package com.kkomi.treeisland.shop.eneity

import com.kkomi.treeisland.shop.ShopManager
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File

data class KeywordShop(
        val manager: ShopManager,
        val stuffList: MutableList<KeywordStuff>
) {

    constructor(manager: ShopManager, config: YamlConfiguration) : this(
            manager,
            config.getList("stuffList", mutableListOf<Stuff>()) as MutableList<KeywordStuff>
    )

    fun addStuff(keyword: String, price: Int) {
        stuffList.add(KeywordStuff(keyword, price))
    }

    fun removeStuff(keywordStuff: KeywordStuff) {
        stuffList.remove(keywordStuff)
    }

    private val file: File
        get() {
            manager.folder.mkdir()
            return File(manager.folder, "keyword${manager.EXT}")
        }

    fun save() {
        val config = YamlConfiguration.loadConfiguration(file)
        config.set("stuffList", stuffList)
        config.save(file)
    }

    fun remove() {
        file.delete()
    }
}