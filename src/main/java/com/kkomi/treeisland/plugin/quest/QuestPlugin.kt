package com.kkomi.treeisland.plugin.quest

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.quest.command.*
import com.kkomi.treeisland.plugin.quest.listener.*
import com.kkomi.treeisland.plugin.quest.schduler.QuestLocationScheduler
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class QuestPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    companion object {
        lateinit var questManager: QuestManager
        lateinit var playerQuestManager: PlayerQuestManager
    }

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("status", CommandQuestStatus("", "진행중인 퀘스트 목록을 확인합니다.", 0))
        }.register(plugin.getCommand("quest"))

        CommandManager(true).apply {
            addComponent("create", CommandQuestCreate("[questName]", "퀘스트를 생성합니다.", 1))
            addComponent("remove", CommandSetQuestTitle("[questName]", "퀘스트를 삭제합니다.", 1))
            addComponent("title", CommandSetQuestTitle("[questName] [title]", "퀘스트의 타이틀을 설정합니다.", 1))
            addComponent("type", CommandSetQuestType("[questName] [type]", "퀘스트의 타입을 설정합니다.", 1))
            addComponent("limitLv", CommandSetQuestLimitLv("[questName] [level]", "퀘스트의 제한레벨을 설정합니다.", 1))
            addComponent("beforeQuest", CommandSetQuestBeforeQuest("[questName] [questName]", "퀘스트의 필요퀘스트를 설정합니다..", 1))
            addComponent("startNpc", CommandSetQuestStartNpc("[questName] [npcName]", "퀘스트의 시작 NPC를 설정합니다.", 1))
            addComponent("endNpc", CommandSetQuestEndNpc("[questName] [npcName]", "퀘스트의 종료 NPC를 설정합니다.", 1))
            addComponent("message", CommandSetQuestMessage("[questName] [purpose/complete/dispose/accept] [message]", "퀘스트의 메시지를 설정합니다.", 1))
            addComponent("action", CommandSetQuestAction("[questName] [action]", "퀘스트의 수행동작을 설정합니다.", 1))
            addComponent("object", CommandSetQuestObject("[questName] [string,location,itemstack]", "퀘스트의 목표를 설정합니다.", 1))
            addComponent("rewardItems", CommandSetQuestRewardItems("[questName]", "퀘스트의 보상 아이템을 설정합니다.", 0))
            addComponent("rewardCommand", CommandSetQuestRewardCommand("[questName] [command]", "퀘스트의 보상 명령어를 설정합니다.", 0))
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
            registerEvents(QuestRewardInventoryListener(), plugin)
            registerEvents(QuestCancelInventoryListener(), plugin)
        }
    }

    override fun setupManagers() {
        questManager = QuestManager(File("${dataFolder.path}/quests"), plugin.logger)
        playerQuestManager = PlayerQuestManager(File("${dataFolder.path}/players"), plugin.logger)
    }

    override fun setupSchedulers() {
        QuestLocationScheduler().setPlugin(plugin).startTimer(0, -1, 0)
    }
}