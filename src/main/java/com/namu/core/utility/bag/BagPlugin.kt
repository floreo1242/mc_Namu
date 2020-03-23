package com.namu.core.utility.bag

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.utility.bag.command.CommandCreateBag
import com.namu.core.utility.bag.listener.BagListener
import com.namu.core.utility.bag.model.BagRepository
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class BagPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandCreateBag())
        }.register(plugin.getCommand("baga"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(BagListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }
}