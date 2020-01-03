package com.kkomi.treeisland.plugin.monster

import com.kkomi.treeisland.library.SubMainManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MonsterPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
    }

    override fun setupListeners() {
    }

    override fun setupManagers() {
    }

    override fun setupSchedulers() {
    }
}