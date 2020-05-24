package com.namu.core.rpg.equip

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.equip.command.CommandEquipInventoryOpen
import com.namu.core.rpg.equip.command.CommandPlayerEquipInfoReload
import com.namu.core.rpg.equip.listener.EquipInventoryListener
import com.namu.core.rpg.equip.listener.EquipPlayerListener
import com.namu.core.rpg.equip.model.entity.PlayerEquipInfo
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class EquipPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("reload", CommandPlayerEquipInfoReload())
        }.register(plugin.getCommand("equipa"))

        CommandManager(false).apply {
            addComponent("open", CommandEquipInventoryOpen())
        }.register(plugin.getCommand("equip"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().registerEvents(EquipPlayerListener(), plugin)
        Bukkit.getPluginManager().registerEvents(EquipInventoryListener(), plugin)
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerEquipInfo::class.java, "PlayerEquipInfo")
    }

    override fun setupSchedulers() {
    }
}