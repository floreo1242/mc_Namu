package com.kkomi.treeisland

import com.kkomi.treeisland.money.MoneyPlugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Treeisland : JavaPlugin() {

    private lateinit var moneyPlugin: MoneyPlugin

    override fun onEnable() {
        moneyPlugin = MoneyPlugin(File(dataFolder.path + "/money"), this)
    }

    override fun onDisable() {
        moneyPlugin.onDisable()
    }

}