package com.kkomi.treeisland.plugin.skill

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.kkomi.treeisland.plugin.skill.command.*
import com.kkomi.treeisland.plugin.skill.inventory.LearnSkillListInventory
import com.kkomi.treeisland.plugin.skill.inventory.SkillOptionInventory
import com.kkomi.treeisland.plugin.skill.listener.PlayerSkillInfoListener
import com.kkomi.treeisland.plugin.skill.listener.SkillOptionInventoryListener
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import com.kkomi.treeisland.plugin.skill.scheduler.SpellCooldownSchedulers
import com.nisovin.magicspells.MagicSpells
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class SkillPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("create", CommandCreateSkillInfo())
            addComponent("skillbook", CommandGetSkillBook())
            addComponent("reload", CommandReloadSkillInfo())
            addComponent("reload_player", CommandReloadPlayerSkillInfo())
        }.register(plugin.getCommand("skilla"))

        CommandManager(false).apply {
            addComponent("open", CommandLearnSkillList())
            addComponent("option", CommandSkillOption())
        }.register(plugin.getCommand("skill"))
    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(LearnSkillListInventory.TITLE)
        InventoryTitleParser.inventoryTitleList.add(SkillOptionInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(PlayerSkillInfoListener(), plugin)
            registerEvents(SkillOptionInventoryListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(PlayerSkillInfo::class.java, "PlayerSkillInfo")
        ConfigurationSerialization.registerClass(SkillInfo::class.java, "SkillInfo")
    }

    override fun setupSchedulers() {

    }
}