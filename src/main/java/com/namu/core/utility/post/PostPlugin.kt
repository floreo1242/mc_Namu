package com.namu.core.utility.post

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.utility.post.model.PlayerPostBoxRepository
import com.namu.core.utility.post.command.CommandOpenPostBoxInventory
import com.namu.core.utility.post.command.CommandPlayerPostBoxReload
import com.namu.core.utility.post.command.CommandSendItem
import com.namu.core.utility.post.listener.PostBoxInventoryListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class PostPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("open", CommandOpenPostBoxInventory())
            addComponent("send", CommandSendItem())
        }.register(plugin.getCommand("post"))

        CommandManager(true).apply {
            addComponent("reload_player", CommandPlayerPostBoxReload())
        }.register(plugin.getCommand("posta"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PostBoxInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }

}