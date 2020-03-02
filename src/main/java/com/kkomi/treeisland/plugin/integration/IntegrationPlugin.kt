package com.kkomi.treeisland.plugin.integration

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.treeisland.plugin.integration.listener.PlayerDamageListener
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class IntegrationPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {

    }

    override fun setupCommands() {
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerDamageListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }
}