package com.kkomi.treeisland.plugin.quest

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.quest.command.*
import com.kkomi.treeisland.plugin.quest.listener.*
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
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
            addComponent("status", CommandQuestStatus("", "진행중인 퀘스트 목록을 확인합니다.", 0))
        }.register(plugin.getCommand("quest"))

        CommandManager(true).apply {
            addComponent("create", CommandQuestCreate("[questName]", "퀘스트를 생성합니다.", 1))
            addComponent("delete", CommandQuestRemove("[questName]", "퀘스트를 삭제합니다.", 1))
            addComponent("reload", CommandQuestReload("", "퀘스트 플러그인을 리로드 합니다.", 0))
        }.register(plugin.getCommand("questa"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(QuestDataListener(), plugin)
            registerEvents(QuestActionListener(), plugin)
            registerEvents(QuestListInventoryListener(), plugin)
            registerEvents(QuestAcceptInventoryListener(), plugin)
            registerEvents(QuestStatusInventoryListener(), plugin)
            registerEvents(QuestCancelInventoryListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(Quest::class.java, "Quest")
        ConfigurationSerialization.registerClass(PlayerQuest::class.java, "PlayerQuest")
    }

    override fun setupSchedulers() {
        QuestLocationScheduler().setPlugin(plugin).startTimer(0, -1, 0)
    }
}