package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import java.io.File

class ShopFileDataSource(
        dataFolder: File
) : ShopDataSource, FileDataSource<Shop>(dataFolder, Shop::class.java) {

    override fun reloadDataList() {
        loadFiles()
    }

    override fun getShop(name: String): Shop? {
        return getValue(name)
    }

    override fun getShopList(): List<Shop> {
        return getValueList()
    }

    override fun addShop(shop: Shop) {
        setValue(shop.name, shop)
    }

    override fun setShop(shop: Shop) {
        setValue(shop.name, shop)
    }

    override fun saveShop(shop: Shop) {
        saveFile(shop.name, shop)
    }

    override fun removeShop(name: String) {
        removeValue(name)
        deleteFile(name)
    }

}