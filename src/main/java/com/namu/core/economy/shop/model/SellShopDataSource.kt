package com.namu.core.economy.shop.model

import com.namu.core.economy.shop.model.entity.SellShop

interface SellShopDataSource {

    companion object {
        const val SHOP_NAME = "SellShop"
    }

    fun reloadDataList()

    fun getSellShop(): SellShop

    fun setSellShop(shop: SellShop)

    fun saveSellShop(shop: SellShop)

}