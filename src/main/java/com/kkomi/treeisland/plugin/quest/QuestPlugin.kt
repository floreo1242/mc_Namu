package com.kkomi.treeisland.plugin.quest

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.kkomi.treeisland.plugin.quest.command.*
import com.kkomi.treeisland.plugin.quest.inventory.*
import com.kkomi.treeisland.plugin.quest.listener.*
import com.kkomi.treeisland.plugin.quest.model.entity.*
import com.kkomi.treeisland.plugin.quest.schduler.QuestLocationScheduler
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class QuestPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("status", CommandOpenQuestStatus())
        }.register(plugin.getCommand("quest"))

        CommandManager(true).apply {
            addComponent("open", CommandOpenQuest())
            addComponent("create", CommandCreateQuest())
            addComponent("delete", CommandDeleteQuest())
            addComponent("reload", CommandReloadQuest())
        }.register(plugin.getCommand("questa"))
    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(QuestAcceptInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(QuestCancelInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(QuestListInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(QuestStatusInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(QuestTalkInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(QuestDataListener(), plugin)
            registerEvents(QuestActionListener(), plugin)
            registerEvents(QuestListInventoryListener(), plugin)
            registerEvents(QuestTalkInventoryListener(), plugin)
            registerEvents(QuestAcceptInventoryListener(), plugin)
            registerEvents(QuestStatusInventoryListener(), plugin)
            registerEvents(QuestCancelInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Quest::class.java, "Quest")
        ConfigurationSerialization.registerClass(QuestObjective::class.java,"QuestObjective")
        ConfigurationSerialization.registerClass(QuestReward::class.java,"QuestReward")
        ConfigurationSerialization.registerClass(QuestRewardItem::class.java,"QuestRewardItem")
        ConfigurationSerialization.registerClass(TalkScript::class.java,"TalkScript")
        ConfigurationSerialization.registerClass(PlayerQuest::class.java, "PlayerQuest")
        ConfigurationSerialization.registerClass(PlayerQuestObjective::class.java, "PlayerQuestObjective")
    }

    override fun setupSchedulers() {
        QuestLocationScheduler().setPlugin(plugin).startTimer(0, -1, 0)
    }
}