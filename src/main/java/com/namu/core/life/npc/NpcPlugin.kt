package com.namu.core.life.npc

import com.kkomi.devlibrary.SubMainManager
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class NpcPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
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