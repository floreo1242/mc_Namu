package com.kkomi.treeisland.plugin.shop

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.kkomi.treeisland.plugin.shop.command.*
import com.kkomi.treeisland.plugin.shop.inventory.ShopInventory
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordStuff
import com.kkomi.treeisland.plugin.shop.model.entity.Stuff
import com.kkomi.treeisland.plugin.shop.listener.ShopInventoryListener
import com.kkomi.treeisland.plugin.shop.model.entity.KeywordShop
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ShopPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandCreateShop())
            addComponent("remove", CommandRemoveShop())
            addComponent("open", CommandOpenShop())
            addComponent("item", CommandShopItem())
            addComponent("keyword", CommandShopKeywordItem())
            addComponent("npc", CommandSetShopNpc())
            addComponent("reload", CommandReloadShop())
        }.register(plugin.getCommand("shopa"))
    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(ShopInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(ShopInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Shop::class.java, "Shop")
        ConfigurationSerialization.registerClass(Stuff::class.java, "Stuff")
        ConfigurationSerialization.registerClass(KeywordShop::class.java, "KeywordShop")
        ConfigurationSerialization.registerClass(KeywordStuff::class.java, "KeywordStuff")
    }

    override fun setupSchedulers() {
    }
}