package com.kkomi.treeisland

import com.kkomi.treeisland.money.MoneyPlugin
import com.kkomi.treeisland.shop.ShopPlugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Treeisland : JavaPlugin() {

    private lateinit var moneyPlugin: MoneyPlugin
    private lateinit var shopPlugin: ShopPlugin

    override fun onEnable() {
        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
    }

    override fun onDisable() {
        moneyPlugin.onDisable()
        shopPlugin.onDisable()
    }

}