package com.namu.core.rpg.role

import com.kkomi.devlibrary.SubMainManager
import com.kkomi.devlibrary.command.CommandManager
import com.namu.core.rpg.role.command.CommandReloadPlayerRole
import com.namu.core.rpg.role.command.CommandReloadRole
import com.namu.core.rpg.role.command.CommandSetRole
import com.namu.core.rpg.role.listener.PlayerRoleListener
import com.namu.core.rpg.role.listener.RoleListener
import com.namu.core.rpg.role.model.PlayerRoleRepository
import com.namu.core.rpg.role.model.RoleRepository
import com.namu.core.rpg.role.model.entity.PlayerRole
import com.namu.core.rpg.role.model.entity.Role
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class RolePlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
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