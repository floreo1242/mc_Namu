package com.kkomi.treeisland.plugin.money

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.treeisland.plugin.money.command.*
import com.kkomi.treeisland.plugin.money.listener.PlayerMoneyListener
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MoneyPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("view", CommandViewMoney())
        }.register(plugin.getCommand("money"))

        CommandManager(true).apply {
            addComponent("give", CommandGiveMoney())
            addComponent("take", CommandTakeMoney())
            addComponent("set", CommandSetMoney())
            addComponent("view", CommandViewTargetMoney())
        }.register(plugin.getCommand("moneya"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerMoneyListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerMoney::class.java, "PlayerMoney")
    }

    override fun setupSchedulers() {
    }
}