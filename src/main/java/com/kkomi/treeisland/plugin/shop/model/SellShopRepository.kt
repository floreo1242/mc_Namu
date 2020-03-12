package com.kkomi.treeisland.plugin.shop.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.shop.model.entity.SellShop
import com.kkomi.treeisland.plugin.shop.model.entity.SellStuff
import org.bukkit.inventory.ItemStack
import java.io.File

object SellShopRepository {

    private var sellShopDataSource: SellShopDataSource = SellShopFileDataSource(File("${Treeisland.shopPlugin.dataFolder}/sell"), SellShop::class.java)

    fun reloadSellShop() {
        sellShopDataSource.reloadDataList()
    }

    fun getSellShop(): SellShop {
        return sellShopDataSource.getSellShop()
    }

    fun saveSellShop(shop: SellShop) {
        return sellShopDataSource.saveSellShop(shop)
    }

    fun getSellStuff(sellStuff: ItemStack): SellStuff? {
        return sellShopDataSource.getSellShop().stuffList
                .find { it.itemStack.isSimilar(sellStuff) }
    }

    fun editSellShop(SellShop: SellShop) {
        sellShopDataSource.setSellShop(SellShop)
    }

}