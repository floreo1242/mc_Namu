package com.namu.core.utility.itemdb

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.utility.itemdb.command.CommandCreateCustomItem
import com.namu.core.utility.itemdb.command.CommandCustomItemReload
import com.namu.core.utility.itemdb.command.CommandDeleteCustomItem
import com.namu.core.utility.itemdb.command.CommandGetCustomItem
import com.namu.core.utility.itemdb.listener.ConsumptionItemListener
import com.namu.core.utility.itemdb.model.entity.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ItemDBPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandCreateCustomItem())
            addComponent("delete", CommandDeleteCustomItem())
            addComponent("reload", CommandCustomItemReload())
            addComponent("get", CommandGetCustomItem())
        }.register(plugin.getCommand("itemdb"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(ConsumptionItemListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(StatOption::class.java, "StatOption")
        ConfigurationSerialization.registerClass(ConsumptionOption::class.java, "ConsumptionOption")
        ConfigurationSerialization.registerClass(EquipmentOption::class.java, "EquipmentItemOption")
        ConfigurationSerialization.registerClass(CustomItem::class.java, "CustomItem")
        Material.GOLDEN_HELMET
        Material.IRON_HELMET
    }

    override fun setupSchedulers() {
    }
}