package com.kkomi.treeisland.plugin.guild

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.guild.command.CommandGuildCreate
import com.kkomi.treeisland.plugin.guild.command.CommandGuildInvite
import com.kkomi.treeisland.plugin.guild.command.CommandGuildInviteAccept
import com.kkomi.treeisland.plugin.guild.command.CommandGuildInviteDispose
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class GuildPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(false).apply {
            addComponent("create", CommandGuildCreate("[guildName]", "Create guild", 1))
            addComponent("info", null)
            addComponent("invite", CommandGuildInvite("[playerName]", "", 1))
            addComponent("accept", CommandGuildInviteAccept("", "", 0))
            addComponent("dispose", CommandGuildInviteDispose("", "", 0))
        }.register(plugin.getCommand("guild"))
    }

    override fun setupListeners() {
    }

    override fun setupManagers() {
    }

    override fun setupSchedulers() {
    }
}