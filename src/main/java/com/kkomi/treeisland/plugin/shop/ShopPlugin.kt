package com.kkomi.treeisland.plugin.shop

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.shop.command.*
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
            addComponent("create", CommandCreateShop("[shopName]", "상점을 생성합니다.", 1))
            addComponent("remove", CommandRemoveShop("[shopName]", "상점을 삭제합니다.", 1))
            addComponent("open", CommandOpenShop("[shopName]", "상점을 열어봅니다.", 1))
            addComponent("list", CommandShopList("", "상점 목록을 확인합니다.", 0))
            addComponent("item", CommandShopItem("[shopName] [add/remove]", "상점에 아이템을 관리합니다.", 0))
            addComponent("keyword", CommandShopKeywordItem("[add/remove]", "키워드 아이템을 관리합니다", 0))
            addComponent("npc", CommandSetShopNpc("[shopName] [npcName]", "상점 NPC를 설정합니다,.", 2))
            addComponent("reload", CommandShopReload("", "정보를 리로드 합니다.", 0))
        }.register(plugin.getCommand("shopa"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(ShopInventoryListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(Shop::class.java, "Shop")
        ConfigurationSerialization.registerClass(Stuff::class.java, "Stuff")
        ConfigurationSerialization.registerClass(KeywordShop::class.java, "KeywordShop")
        ConfigurationSerialization.registerClass(KeywordStuff::class.java, "KeywordStuff")
    }

    override fun setupSchedulers() {
    }
}