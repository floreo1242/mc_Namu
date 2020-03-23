package com.namu.core.rpg.enhance

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.enhance.command.CommandCreateEnhanceScroll
import com.namu.core.rpg.enhance.command.CommandOpenEnhanceInventory
import com.namu.core.rpg.enhance.listener.EnhanceInventoryListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class EnhancePlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandCreateEnhanceScroll())
        }.register(plugin.getCommand("enhancea"))


        CommandManager(true).apply {
            addComponent("open", CommandOpenEnhanceInventory())
        }.register(plugin.getCommand("enhance"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(EnhanceInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }
}