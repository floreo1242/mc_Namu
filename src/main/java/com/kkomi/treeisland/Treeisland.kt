package com.kkomi.treeisland

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.plugin.money.MoneyPlugin
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
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