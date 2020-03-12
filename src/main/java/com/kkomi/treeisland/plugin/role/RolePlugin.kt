package com.kkomi.treeisland.plugin.role

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.kkomi.treeisland.plugin.role.command.CommandReloadPlayerRole
import com.kkomi.treeisland.plugin.role.command.CommandReloadRole
import com.kkomi.treeisland.plugin.role.command.CommandSetRole
import com.kkomi.treeisland.plugin.role.listener.PlayerRoleListener
import com.kkomi.treeisland.plugin.role.listener.RoleListener
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.RoleRepository
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import com.kkomi.treeisland.plugin.role.model.entity.Role
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class RolePlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
        PlayerRoleRepository.getPlayerRoleList()
                .forEach {
                    PlayerRoleRepository.savePlayerRole(it)
                }

        RoleRepository.getRoleList()
                .forEach {
                    RoleRepository.saveRole(it)
                }
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("setRole", CommandSetRole())
            addComponent("reload", CommandReloadRole())
            addComponent("reload_player", CommandReloadPlayerRole())
        }.register(plugin.getCommand("rolea"))
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(RoleListener(), plugin)
            registerEvents(PlayerRoleListener(), plugin)
        }
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Role::class.java, "Role")
        ConfigurationSerialization.registerClass(PlayerRole::class.java, "PlayerRole")
    }

    override fun setupSchedulers() {
    }
}