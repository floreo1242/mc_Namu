package com.namu.core.economy.shop.model

import com.namu.core.MainCore
import com.namu.core.economy.shop.model.entity.Shop
import java.io.File

object ShopRepository {

    private var shopDataSource: ShopDataSource = ShopFileDataSource(File("${MainCore.shopPlugin.dataFolder}/shop"))

    fun reloadShop() {
        shopDataSource.reloadDataList()
    }

    fun getShop(name: String): Shop? {
        return shopDataSource.getShop(name)
    }

    fun getShopList(): List<Shop> {
        return shopDataSource.getShopList()
    }

    fun createShop(shop: Shop) {
        shopDataSource.createShop(shop)
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