package com.kkomi.treeisland.plugin.equipitem

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.equipitem.command.CommandOpenEquipItem
import com.kkomi.treeisland.plugin.equipitem.listener.EquipItemInventoryListener
import com.kkomi.treeisland.plugin.equipitem.listener.PlayerEquipItemListener
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class EquipItemPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("open", CommandOpenEquipItem("", "Open player equip inventory", 0))
        }.register(plugin.getCommand("equip"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(EquipItemInventoryListener(), plugin)
            registerEvents(PlayerEquipItemListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(PlayerEquipItem::class.java,"EquipItem")
    }

    override fun setupSchedulers() {
    }
}