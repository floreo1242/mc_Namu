package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordShop
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordStuff
import java.io.File

object KeywordShopRepository {

    private var keywordShopDataSource: KeywordShopDataSource = KeywordShopFileDataSource(File("${Treeisland.shopPlugin.dataFolder}/keyword"), KeywordShop::class.java)

    fun reloadKeywordShop() {
        keywordShopDataSource.reloadDataList()
    }

    fun getKeywordShop(): KeywordShop {
        return keywordShopDataSource.getKeywordShop()
    }

    fun getKeywordStuff(keyword: String): KeywordStuff? {
        return keywordShopDataSource.getKeywordShop().stuffList
                .find { keyword.toUpperCase().contains(it.keyword.toUpperCase()) }
    }

    fun editKeywordShop(KeywordShop: KeywordShop) {
        keywordShopDataSource.setKeywordShop(KeywordShop)
    }

}