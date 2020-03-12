package com.kkomi.treeisland.plugin.shop

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.shop.command.*
import com.kkomi.treeisland.plugin.shop.inventory.ShopInventory
import com.kkomi.treeisland.plugin.shop.model.entity.SellStuff
import com.kkomi.treeisland.plugin.shop.model.entity.Stuff
import com.kkomi.treeisland.plugin.shop.listener.ShopInventoryListener
import com.kkomi.treeisland.plugin.shop.model.SellShopRepository
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.shop.model.entity.SellShop
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ShopPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
        ShopRepository.getShopList()
                .forEach {
                    ShopRepository.saveShop(it)
                }

        SellShopRepository.saveSellShop(SellShopRepository.getSellShop())
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandCreateShop())
            addComponent("remove", CommandRemoveShop())
            addComponent("open", CommandOpenShop())
            addComponent("item", CommandShopItem())
            addComponent("sell", CommandShopSellItem())
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
        ConfigurationSerialization.registerClass(SellShop::class.java, "SellShop")
        ConfigurationSerialization.registerClass(SellStuff::class.java, "SellStuff")
    }

    override fun setupSchedulers() {
    }
}