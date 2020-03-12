package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import java.io.File

object ShopRepository {

    private var shopDataSource: ShopDataSource = ShopFileDataSource(File("${Treeisland.shopPlugin.dataFolder}/shop"))

    fun reloadShop() {
        shopDataSource.reloadDataList()
    }

    fun getShop(name: String): Shop? {
        return shopDataSource.getShop(name)
    }

    fun getShopList(): List<Shop> {
        return shopDataSource.getShopList()
    }

    fun addShop(shop: Shop) {
        shopDataSource.addShop(shop)
    }

    fun saveShop(shop: Shop) {
        shopDataSource.saveShop(shop)
    }

    fun removeShop(name: String) {
        shopDataSource.removeShop(name)
    }

    fun editShop(shop: Shop) {
        shopDataSource.setShop(shop)
    }

}