package com.kkomi.treeisland.plugin.itemdb

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.itemdb.command.*
import com.kkomi.treeisland.plugin.itemdb.listener.ConsumptionItemListener
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItemOption
import com.kkomi.treeisland.plugin.itemdb.model.entity.OtherItem
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ItemDBPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", CommandEquipmentItemCreate("[name]", "Create equipment item", 1))
            addComponent("get", CommandEquipmentItemGet("[name]", "Get equipment item", 1))
            addComponent("debug", CommandEquipmentItemDebug("[name]", "equipment debug", 1))
        }.register(plugin.getCommand("itemdb-equipment"))


        CommandManager(true).apply {
            addComponent("create", CommandConsumptionItemCreate("[name]", "Create consumption item", 1))
            addComponent("get", CommandConsumptionItemGet("[name]", "Get consumption item", 1))
        }.register(plugin.getCommand("itemdb-consumption"))


        CommandManager(true).apply {
            addComponent("create", CommandOtherItemCreate("[name]", "Create other item", 1))
            addComponent("get", CommandOtherItemGet("[name]", "Get other item", 1))
        }.register(plugin.getCommand("itemdb-other"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(ConsumptionItemListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(EquipmentItem::class.java, "EquipmentItem")
        ConfigurationSerialization.registerClass(ConsumptionItem::class.java, "ConsumptionItem")
        ConfigurationSerialization.registerClass(OtherItem::class.java, "OtherItem")
        ConfigurationSerialization.registerClass(EquipmentItemOption::class.java, "EquipmentItemOption")
    }

    override fun setupSchedulers() {
    }
}