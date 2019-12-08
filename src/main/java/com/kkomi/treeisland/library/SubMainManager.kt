package com.kkomi.treeisland.library

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

abstract class SubMainManager(
        val dataFolder: File,
        val plugin : JavaPlugin
) {

    init {
        setupCommands()
        setupListeners()
        setupManagers()
        setupSchedulers()
    }


    abstract fun onDisable()

    protected abstract fun setupCommands()

    protected abstract fun setupListeners()

    abstract fun setupManagers()

    protected abstract fun setupSchedulers()

}