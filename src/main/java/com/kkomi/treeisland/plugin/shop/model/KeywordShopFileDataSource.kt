package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordShop
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordStuff
import java.io.File

class KeywordShopFileDataSource(
        dataFolder: File,
        classType: Class<KeywordShop>
) : KeywordShopDataSource, FileDataSource<KeywordShop>(dataFolder, classType) {

    override fun reloadDataList() {
        loadFiles()
    }

    override fun getKeywordShop(): KeywordShop {
        if (getValueList().isEmpty()) {
            setKeywordShop(KeywordShop(mutableListOf()))
        }
        return getValueList()[0]
    }

    override fun setKeywordShop(shop: KeywordShop) {
        setValue(KeywordShopDataSource.SHOP_NAME, shop)
        saveFile(KeywordShopDataSource.SHOP_NAME, shop)
    }

}