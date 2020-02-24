package com.kkomi.treeisland.plugin.itemdb

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.treeisland.plugin.itemdb.command.consumption.CommandCreateConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.command.consumption.CommandDeleteConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.command.consumption.CommandGetConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.command.consumption.CommandReloadConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.command.equipment.CommandCreateEquipmentItem
import com.kkomi.treeisland.plugin.itemdb.command.equipment.CommandDeleteEquipmentItem
import com.kkomi.treeisland.plugin.itemdb.command.equipment.CommandGetEquipmentItem
import com.kkomi.treeisland.plugin.itemdb.command.equipment.CommandReloadEquipmentItem
import com.kkomi.treeisland.plugin.itemdb.command.other.CommandCreateOtherItem
import com.kkomi.treeisland.plugin.itemdb.command.other.CommandDeleteOtherItem
import com.kkomi.treeisland.plugin.itemdb.command.other.CommandGetOtherItem
import com.kkomi.treeisland.plugin.itemdb.command.other.CommandReloadOtherItem
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
            addComponent("create", CommandCreateEquipmentItem())
            addComponent("delete", CommandDeleteEquipmentItem())
            addComponent("reload", CommandReloadEquipmentItem())
            addComponent("get", CommandGetEquipmentItem())
        }.register(plugin.getCommand("itemdb-equipment"))


        CommandManager(true).apply {
            addComponent("create", CommandCreateConsumptionItem())
            addComponent("delete", CommandDeleteConsumptionItem())
            addComponent("reload", CommandReloadConsumptionItem())
            addComponent("get", CommandGetConsumptionItem())
        }.register(plugin.getCommand("itemdb-consumption"))


        CommandManager(true).apply {
            addComponent("create", CommandCreateOtherItem())
            addComponent("delete", CommandDeleteOtherItem())
            addComponent("reload", CommandReloadOtherItem())
            addComponent("get", CommandGetOtherItem())
        }.register(plugin.getCommand("itemdb-other"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(ConsumptionItemListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(EquipmentItem::class.java, "EquipmentItem")
        ConfigurationSerialization.registerClass(ConsumptionItem::class.java, "ConsumptionItem")
        ConfigurationSerialization.registerClass(OtherItem::class.java, "OtherItem")
        ConfigurationSerialization.registerClass(EquipmentItemOption::class.java, "EquipmentItemOption")
    }

    override fun setupSchedulers() {
    }
}