package com.kkomi.treeisland.plugin.skill

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.plugin.skill.listener.PlayerSkillInfoListener
import com.kkomi.treeisland.plugin.skill.listener.SkillListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class SkillPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
    }

    override fun setupListeners() {
    }

    override fun setupManagers() {
        Bukkit.getPluginManager().apply {
            registerEvents(SkillListener(), plugin)
            registerEvents(PlayerSkillInfoListener(), plugin)
        }
    }

    override fun setupSchedulers() {
    }
}