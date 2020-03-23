package com.namu.core.rpg.level

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.level.command.CommandExpCoupon
import com.namu.core.rpg.level.command.admin.CommandReloadLevelConfig
import com.namu.core.rpg.level.command.admin.CommandViewlLevelOtherPlayer
import com.namu.core.rpg.level.command.user.CommandLevelView
import com.namu.core.rpg.level.command.admin.CommandReloadPlayerLevel
import com.namu.core.rpg.level.listener.PlayerLevelListener
import com.namu.core.rpg.level.model.entity.LevelConfig
import com.namu.core.rpg.level.model.entity.PlayerLevel
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class LevelPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("view", CommandLevelView())
        }.register(plugin.getCommand("level"))

        CommandManager(false).apply {
            addComponent("view", CommandViewlLevelOtherPlayer())
            addComponent("coupon", CommandExpCoupon())
            addComponent("reload_player", CommandReloadPlayerLevel())
            addComponent("reload_config", CommandReloadLevelConfig())
        }.register(plugin.getCommand("levela"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerLevelListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerLevel::class.java, "PlayerLevel")
        ConfigurationSerialization.registerClass(LevelConfig::class.java, "LevelConfig")
    }

    override fun setupSchedulers() {
    }
}