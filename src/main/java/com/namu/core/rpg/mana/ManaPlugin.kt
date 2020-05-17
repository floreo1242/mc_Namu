package com.namu.core.rpg.mana

import com.kkomi.devlibrary.SubMainManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class ManaPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
    }

    override fun setupCommands() {
    }

    override fun setupInventoryTitle() {
    }

    override fun setupListeners() {
    }

    override fun setupRegisterClass() {
    }

    override fun setupSchedulers() {
    }
}