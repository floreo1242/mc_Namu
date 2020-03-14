package com.kkomi.treeisland.plugin.post

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.post.model.PlayerPostBoxRepository
import com.kkomi.treeisland.plugin.post.command.CommandOpenPostBoxInventory
import com.kkomi.treeisland.plugin.post.command.CommandPlayerPostBoxReload
import com.kkomi.treeisland.plugin.post.command.CommandSendItem
import com.kkomi.treeisland.plugin.post.listener.PostBoxInventoryListener
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class PostPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
        PlayerPostBoxRepository.getPlayerPostBoxList()
                .forEach {
                    PlayerPostBoxRepository.savePlayerPostBox(it)
                }
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