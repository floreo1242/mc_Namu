package com.kkomi.treeisland.plugin.guild

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.treeisland.plugin.guild.command.user.*
import com.kkomi.treeisland.plugin.guild.listener.GuildInventoryListener
import com.kkomi.treeisland.plugin.guild.listener.PlayerGuildListener
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class GuildPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("create", CommandGuildCreate("[guildName]", "Create guild", 1))
            addComponent("info", CommandGuildInfo("", "", 0))
            addComponent("invite", CommandGuildInvite("[playerName]", "", 1))
            addComponent("accept", CommandGuildInviteAccept("", "", 0))
            addComponent("dispose", CommandGuildInviteDispose("", "", 0))
            addComponent("kick", CommandGuildKick("[playerName]", "", 1))
            addComponent("grade", CommandGuildGrade("[playerName]", "", 1))
        }.register(plugin.getCommand("guild"))

        CommandManager(true).apply {
            addComponent()
        }
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(GuildInventoryListener(), plugin)
            registerEvents(PlayerGuildListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(Guild::class.java, "Guild")
        ConfigurationSerialization.registerClass(GuildMemberState::class.java, "GuildMemberState")
        ConfigurationSerialization.registerClass(GuildMemberState::class.java, "GuildOption")
        ConfigurationSerialization.registerClass(GuildMemberState::class.java, "PlayerGuild")
        ConfigurationSerialization.registerClass(GuildMemberState::class.java, "GuildUpgradeValue")
    }

    override fun setupSchedulers() {
    }
}