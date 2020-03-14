package com.kkomi.treeisland.plugin.subsystem

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class OtherPlugin(
        dataFolder: File,
        plugin: JavaPlugin
) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {

        }.register(plugin.getCommand("other"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {

        }
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }

}