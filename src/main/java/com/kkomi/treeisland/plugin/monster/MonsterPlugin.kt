package com.kkomi.treeisland.plugin.monster

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.plugin.monster.listener.MonsterListener
import com.kkomi.treeisland.plugin.monster.model.entity.MonsterDrop
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MonsterPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(MonsterListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(MonsterDrop::class.java, "MonsterDrop")
    }

    override fun setupSchedulers() {
    }
}