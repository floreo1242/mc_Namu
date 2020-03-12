package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.shop.model.entity.SellShop
import java.io.File

class SellShopFileDataSource(
        dataFolder: File,
        classType: Class<SellShop>
) : SellShopDataSource, FileDataSource<SellShop>(dataFolder, classType) {

    override fun reloadDataList() {
        loadFiles()
    }

    override fun getSellShop(): SellShop {
        if (getValueList().isEmpty()) {
            setSellShop(SellShop(mutableListOf()))
        }
        return getValueList()[0]
    }

    override fun saveSellShop(shop: SellShop) {
        saveFile(SellShopDataSource.SHOP_NAME, shop)
    }

    override fun setSellShop(shop: SellShop) {
        setValue(SellShopDataSource.SHOP_NAME, shop)
    }

}