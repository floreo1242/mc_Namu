package com.namu.core.rpg.guild

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.namu.core.rpg.guild.command.admin.CommandGuildReload
import com.namu.core.rpg.guild.command.admin.CommandPlayerGuildReload
import com.namu.core.rpg.guild.command.user.*
import com.namu.core.rpg.guild.inventory.GuildInfoInventory
import com.namu.core.rpg.guild.listener.GuildInventoryListener
import com.namu.core.rpg.guild.listener.PlayerGuildListener
import com.namu.core.rpg.guild.model.entity.*
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class GuildPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("create", CommandCreateGuild())
            addComponent("delete", CommandDeleteGuild())
            addComponent("info", CommandViewGuildInfo())
            addComponent("invite", CommandSendGuildInvite())
            addComponent("accept", CommandAcceptGuildInvite())
            addComponent("dispose", CommandDisposeGuildInvite())
            addComponent("kick", CommandKickGuildMemeber())
            addComponent("grade", CommandSetGradeGuild())
            addComponent("upgrade", CommandUpgradeGuild())
            addComponent("donation", CommandGuildDonation())
        }.register(plugin.getCommand("guild"))

        CommandManager(true).apply {
            addComponent("reload", CommandGuildReload())
            addComponent("reload_player", CommandPlayerGuildReload())
        }
    }

    override fun setupInventoryTitle() {
        InventoryTitleParser.inventoryTitleList.add(GuildInfoInventory.TITLE)
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(GuildInventoryListener(), plugin)
            registerEvents(PlayerGuildListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Guild::class.java, "Guild")
        ConfigurationSerialization.registerClass(GuildMemberState::class.java, "GuildMemberState")
        ConfigurationSerialization.registerClass(GuildOption::class.java, "GuildOption")
        ConfigurationSerialization.registerClass(PlayerGuild::class.java, "PlayerGuild")
        ConfigurationSerialization.registerClass(GuildUpgradeValue::class.java, "GuildUpgradeValue")
    }

    override fun setupSchedulers() {
    }
}