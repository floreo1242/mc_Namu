package com.namu.core.rpg.skill

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.namu.core.rpg.skill.command.*
import com.namu.core.rpg.skill.inventory.LearnSkillListInventory
import com.namu.core.rpg.skill.inventory.SkillOptionInventory
import com.namu.core.rpg.skill.listener.PlayerSkillInfoListener
import com.namu.core.rpg.skill.listener.SkillOptionInventoryListener
import com.namu.core.rpg.skill.model.entity.PlayerSkillInfo
import com.namu.core.rpg.skill.model.entity.SkillInfo
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