package com.namu.core.rpg.stat

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.namu.core.rpg.stat.command.CommandOpenPlayerStatInventory
import com.namu.core.rpg.stat.command.CommandReloadPlayerStat
import com.namu.core.rpg.stat.inventory.PlayerStatInventory
import com.namu.core.rpg.stat.listener.PlayerStatInventoryListener
import com.namu.core.rpg.stat.listener.PlayerStatListener
import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.entity.PlayerStat
import com.namu.core.rpg.stat.model.entity.StatConfig
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class StatPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("open", CommandOpenPlayerStatInventory())
            addComponent("reload", CommandReloadPlayerStat() as CommandComponent)
        }.register(plugin.getCommand("stat"))

    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(PlayerStatInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerStatInventoryListener(), plugin)
            registerEvents(PlayerStatListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerStat::class.java,"PlayerStat")
        ConfigurationSerialization.registerClass(StatConfig::class.java,"StatConfig")
    }

    override fun setupSchedulers() {
    }
}