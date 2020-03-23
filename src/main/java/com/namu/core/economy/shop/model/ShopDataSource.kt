package com.namu.core.economy.shop.model

import com.namu.core.economy.shop.model.entity.Shop

interface ShopDataSource {

    fun reloadDataList()

    fun getShop(name: String): Shop?

    fun getShopList(): List<Shop>

    fun createShop(shop: Shop)

    fun setShop(shop: Shop)

    fun removeShop(name: String)

    fun saveShop(shop: Shop)

}