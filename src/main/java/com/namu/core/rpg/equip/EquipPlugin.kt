package com.namu.core.rpg.equip

import com.kkomi.devlibrary.SubMainManager
import com.namu.core.rpg.equip.model.entity.Equip
import com.namu.core.rpg.guild.model.entity.Guild
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class EquipPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {
    override fun onDisable() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupCommands() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupInventoryTitle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupListeners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupRegisterClass() {
        ConfigurationSerialization.registerClass(Equip::class.java, "Equip")

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupSchedulers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}