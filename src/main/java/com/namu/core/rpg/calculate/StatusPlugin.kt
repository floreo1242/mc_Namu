package com.namu.core.rpg.calculate

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.calculate.command.CommandPlayerStatusCheck
import com.namu.core.rpg.calculate.listener.StatusListener
import com.namu.core.rpg.calculate.statusbar.StatusBarScheduler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class StatusPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("view", CommandPlayerStatusCheck())
        }.register(plugin.getCommand("status"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().registerEvents(StatusListener(), plugin)
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
        StatusBarScheduler().setPlugin(plugin).startTimer(0, -1, 0, 10)
    }
}