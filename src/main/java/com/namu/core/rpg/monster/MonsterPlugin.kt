package com.namu.core.rpg.monster

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.monster.command.CommandReloadMonster
import com.namu.core.rpg.monster.listener.MonsterListener
import com.namu.core.rpg.monster.model.MonsterRepository
import com.namu.core.rpg.monster.model.entity.DropItem
import com.namu.core.rpg.monster.model.entity.MonsterDrop
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MonsterPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("reload",CommandReloadMonster())
        }.register(plugin.getCommand("monstera"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(MonsterListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(MonsterDrop::class.java, "MonsterDrop")
        ConfigurationSerialization.registerClass(DropItem::class.java, "DropItem")
    }

    override fun setupSchedulers() {
    }
}