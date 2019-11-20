package com.kkomi.treeisland.money

import com.kkomi.library.SubMainManager
import com.kkomi.library.command.CommandManager
import com.kkomi.treeisland.money.command.*
import com.kkomi.treeisland.money.listener.PlayerMoneyListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MoneyPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    companion object {
        lateinit var moneyManager: MoneyManager
    }

    override fun onDisable() {
    }

    override fun setupCommands() {
        println(plugin)

        CommandManager(false).apply {
            addComponent("view", CommandViewMoney("", "본인의 돈을 확인합니다..", 0))
        }.register(plugin.getCommand("money"))

        CommandManager(true).apply {
            addComponent("give", CommandGiveMoney("[playername] [money]", "해당 플레이어의 돈을 증액합니다.", 2))
            addComponent("take", CommandTakeMoney("[playername] [money]", "해당 플레이어의 돈을 차감합니다.", 2))
            addComponent("set", CommandSetMoney("[playername] [money]", "해당 플레이어의 돈을 설정합니다.", 2))
            addComponent("view", CommandViewTargetMoney("[playername]", "해당 플레이어의 돈을 확인합니다.", 1))
        }.register(plugin.getCommand("moneya"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerMoneyListener(), plugin)
        }
    }

    override fun setupManagers() {
        moneyManager = MoneyManager(dataFolder, plugin.logger)
    }

    override fun setupSchedulers() {
    }
}