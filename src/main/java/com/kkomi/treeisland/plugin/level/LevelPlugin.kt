package com.kkomi.treeisland.plugin.level

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.level.command.CommandLevelConfigReload
import com.kkomi.treeisland.plugin.level.command.CommandLevelOtherView
import com.kkomi.treeisland.plugin.level.command.CommandLevelView
import com.kkomi.treeisland.plugin.level.command.CommandPlayerLevelReload
import com.kkomi.treeisland.plugin.level.listener.PlayerLevelListener
import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class LevelPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("view", CommandLevelView("", "View My Level Info", 0))
        }.register(plugin.getCommand("level"))

        CommandManager(false).apply {
            addComponent("view", CommandLevelOtherView("[playername]", "View My Level Info", 1))
            addComponent("reloadPlayer", CommandPlayerLevelReload("", "", 0))
            addComponent("reloadConfig", CommandLevelConfigReload("", "", 0))
        }.register(plugin.getCommand("levela"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerLevelListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(PlayerLevel::class.java, "PlayerLevel")
        ConfigurationSerialization.registerClass(LevelConfig::class.java, "LevelConfig")
    }

    override fun setupSchedulers() {
    }
}