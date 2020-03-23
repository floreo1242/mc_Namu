package com.namu.core.economy.auction

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.economy.auction.command.CommandAddAuctionStuff
import com.namu.core.economy.auction.command.CommandOpenAuctionInventory
import com.namu.core.economy.auction.inventory.AuctionInventory
import com.namu.core.economy.auction.listener.AuctionInventoryListener
import com.namu.core.economy.auction.model.entity.Auction
import com.namu.core.economy.auction.model.entity.AuctionStuff
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class AuctionPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("open", CommandOpenAuctionInventory())
            addComponent("add", CommandAddAuctionStuff())
        }.register(plugin.getCommand("auction"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(AuctionInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Auction::class.java,"Auction")
        ConfigurationSerialization.registerClass(AuctionStuff::class.java,"AuctionStuff")
    }

    override fun setupSchedulers() {
    }
}