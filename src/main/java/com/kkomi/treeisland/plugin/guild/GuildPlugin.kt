package com.kkomi.treeisland.plugin.guild

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.devlibrary.inventory.InventoryTitleParser
import com.kkomi.treeisland.plugin.guild.command.admin.CommandGuildReload
import com.kkomi.treeisland.plugin.guild.command.admin.CommandPlayerGuildReload
import com.kkomi.treeisland.plugin.guild.command.user.*
import com.kkomi.treeisland.plugin.guild.inventory.GuildInfoInventory
import com.kkomi.treeisland.plugin.guild.listener.GuildInventoryListener
import com.kkomi.treeisland.plugin.guild.listener.PlayerGuildListener
import com.kkomi.treeisland.plugin.guild.model.entity.*
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

fun main() {
    fun solution(num: Int): String {
        return num.and(1).toString()
    }
    println(solution(4))
    println(solution(7))
}

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