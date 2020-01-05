package com.kkomi.treeisland.plugin.stat

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.stat.command.CommandOpenPlayerStat
import com.kkomi.treeisland.plugin.stat.listener.PlayerStatInventoryListener
import com.kkomi.treeisland.plugin.stat.listener.PlayerStatListener
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class StatPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("open", CommandOpenPlayerStat("", "Open player stat inventory", 0))
        }.register(plugin.getCommand("stat"))

    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerStatInventoryListener(), plugin)
            registerEvents(PlayerStatListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(PlayerStat::class.java,"PlayerStat")
    }

    override fun setupSchedulers() {
    }
}