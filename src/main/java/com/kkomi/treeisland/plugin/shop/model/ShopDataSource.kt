package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.treeisland.plugin.shop.model.entity.Shop

interface ShopDataSource {

    fun reloadDataList()

    fun getShop(name: String): Shop?

    fun getShopList(): List<Shop>

    fun createShop(shop: Shop)

    fun setShop(shop: Shop)

    fun removeShop(name: String)

    fun saveShop(shop: Shop)

}