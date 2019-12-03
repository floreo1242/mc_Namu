package com.kkomi.treeisland

import com.kkomi.treeisland.money.MoneyPlugin
import com.kkomi.treeisland.quest.QuestPlugin
import com.kkomi.treeisland.shop.ShopPlugin
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Treeisland : JavaPlugin() {

    companion object {
        lateinit var moneyPlugin: MoneyPlugin
        lateinit var shopPlugin: ShopPlugin
        lateinit var questPlugin: QuestPlugin
    }

    override fun onEnable() {
        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
        shopPlugin = ShopPlugin(File(dataFolder.path + "/shop"), this)
        questPlugin = QuestPlugin(File(dataFolder.path + "/quest"), this)
    }

    override fun onDisable() {
        moneyPlugin.onDisable()
        shopPlugin.onDisable()
        questPlugin.onDisable()
    }

}