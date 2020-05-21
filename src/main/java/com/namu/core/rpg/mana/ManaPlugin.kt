package com.namu.core.rpg.mana

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.mana.command.CommandPlayerManaReload
import com.namu.core.rpg.mana.command.CommandManaConfigReload
import com.namu.core.rpg.mana.listener.PlayerManaListener
import com.namu.core.rpg.mana.model.entity.ManaConfig
import com.namu.core.rpg.mana.model.entity.PlayerMana
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ManaPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("player", CommandPlayerManaReload())
            addComponent("config", CommandManaConfigReload())
        }
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerManaListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerMana::class.java,"PlayerMana")
        ConfigurationSerialization.registerClass(ManaConfig::class.java,"ManaConfig")
    }

    override fun setupSchedulers() {
    }
}