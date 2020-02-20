package com.kkomi.treeisland.plugin.role

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.treeisland.plugin.role.command.CommandPlayerRoleReload
import com.kkomi.treeisland.plugin.role.command.CommandRoleReload
import com.kkomi.treeisland.plugin.role.command.CommandSetRole
import com.kkomi.treeisland.plugin.role.listener.PlayerRoleListener
import com.kkomi.treeisland.plugin.role.listener.RoleListener
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import com.kkomi.treeisland.plugin.role.model.entity.Role
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class RolePlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("setRole", CommandSetRole("[PlayerName] [RoleName]", "", 2))
            addComponent("reloadrole", CommandRoleReload("", "", 0))
            addComponent("reloadplayerrole", CommandPlayerRoleReload("", "", 0))
        }.register(plugin.getCommand("rolea"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(RoleListener(), plugin)
            registerEvents(PlayerRoleListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(Role::class.java, "Role")
        ConfigurationSerialization.registerClass(PlayerRole::class.java, "PlayerRole")
    }

    override fun setupSchedulers() {
    }
}