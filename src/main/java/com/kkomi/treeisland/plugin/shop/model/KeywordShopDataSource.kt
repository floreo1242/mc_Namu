package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.treeisland.plugin.shop.model.entity.KeywordShop
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordStuff

interface KeywordShopDataSource {

    companion object {
        const val SHOP_NAME = "KeywordShop"
    }

    fun reloadDataList()

    fun getKeywordShop() : KeywordShop

    fun setKeywordShop(shop: KeywordShop)

}