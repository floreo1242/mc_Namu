package com.kkomi.treeisland.plugin.skill

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.skill.command.*
import com.kkomi.treeisland.plugin.skill.listener.PlayerSkillInfoListener
import com.kkomi.treeisland.plugin.skill.listener.SkillListener
import com.kkomi.treeisland.plugin.skill.listener.SkillOptionInventoryListener
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class SkillPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("create", CommandCreateSkillInfo("", "", 1))
            addComponent("skillbook", CommandGetSkillBook("", "", 1))
            addComponent("reloadskillinfo", CommandReloadSkillInfo("", "", 0))
            addComponent("reloadplayerskillinfo", CommandReloadPlayerSkillInfo("", "", 0))
        }.register(plugin.getCommand("skilla"))

        CommandManager(false).apply {
            addComponent("open", CommandLearnSkillList("", "", 0))
            addComponent("option", CommandSkillOption("", "", 0))
        }.register(plugin.getCommand("skill"))
    }

    override fun setupListeners() {
    }

    override fun setupManagers() {
        Bukkit.getPluginManager().apply {
            registerEvents(SkillListener(), plugin)
            registerEvents(PlayerSkillInfoListener(), plugin)
            registerEvents(SkillOptionInventoryListener(), plugin)
        }
        ConfigurationSerialization.registerClass(PlayerSkillInfo::class.java, "PlayerSkillInfo")
        ConfigurationSerialization.registerClass(SkillInfo::class.java, "SkillInfo")
    }

    override fun setupSchedulers() {
    }
}