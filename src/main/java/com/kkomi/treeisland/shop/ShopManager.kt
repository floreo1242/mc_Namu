package com.kkomi.treeisland.shop

import com.kkomi.library.ObjectManager
import com.kkomi.treeisland.shop.eneity.KeywordShop
import com.kkomi.treeisland.shop.eneity.Shop
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.*
import java.util.logging.Logger

class ShopManager(folder: File, logger: Logger) : ObjectManager<Shop>(folder, logger) {

    lateinit var keywordShop: KeywordShop

    override fun loadData() {
        val shopByName = TreeMap<String, Shop>(String.CASE_INSENSITIVE_ORDER)
        getFolderFiles().filter { !it.name.contains("keyword") }.forEach { file ->
            val shopName = file.name.substring(0, file.name.length - EXTlen)
            try {
                val config = YamlConfiguration.loadConfiguration(file)
                val shop = Shop(this, config)
                shopByName[shop.name] = shop
            } catch (exception: Exception) {
                logger.info("Failed to load from $shopName cause : ")
                exception.printStackTrace()
            }
        }
        val file = File(folder, "keyword.yml")
        if (!file.exists()) {
            keywordShop = KeywordShop(this, mutableListOf())
            keywordShop.save()
        }else {
            keywordShop = KeywordShop(this, YamlConfiguration.loadConfiguration(file))
        }
        println("Load File Count : ${shopByName.keys.count()}")
        this.objectByName = shopByName
    }

    fun createShop(name: String): Shop {
        val shop = Shop(this, name, mutableListOf(), "")
        objectByName[shop.name] = shop
        shop.save()
        this.objectList = null
        return shop
    }

    fun getShop(shopName: String): Shop? = objectByName[shopName]

    val shopNames: Set<String> = keys!!

    val ShopList: List<Shop> = objectList!!

    val Shops: Collection<Shop> = objects!!
}