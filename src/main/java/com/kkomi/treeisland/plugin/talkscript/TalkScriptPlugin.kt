package com.kkomi.treeisland.plugin.talkscript

import com.kkomi.treeisland.library.SubMainManager
import com.kkomi.treeisland.library.command.CommandManager
import com.kkomi.treeisland.plugin.talkscript.commands.*
import com.kkomi.treeisland.plugin.talkscript.listener.TalkScriptListener
import com.kkomi.treeisland.plugin.talkscript.model.entity.Dialog
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerialization
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class TalkScriptPlugin(dataFolder: File, plugin: JavaPlugin) : SubMainManager(dataFolder, plugin) {

    override fun onDisable() {
    }

    override fun setupCommands() {
        CommandManager(true).apply {
            addComponent("create", TalkScriptCreateCommand("[name]", "Create new talkScript", 1))
            addComponent("delete", TalkScriptDeleteCommand("[name]", "Delete talkScript", 1))
            addComponent("addDialog", TalkScriptAddDialogCommand("[name] [talker] [message]", "Add talk dialog", 0))
            addComponent("removeDialog", TalkScriptRemoveDialogCommand("[name] [index]", "Remove talk dialog", 2))
            addComponent("runCommand", TalkScriptSetRunCommandCommand("[name] [command]", "Set run command", 0))
            addComponent("open", TalkScriptOpenCommand("[name]", "Open talkScript inventory", 1))
        }.register(plugin.getCommand("talkscripta"))
    }

    override fun setupListeners() {
        Bukkit.getPluginManager().apply {
            registerEvents(TalkScriptListener(), plugin)
        }
    }

    override fun setupManagers() {
        ConfigurationSerialization.registerClass(TalkScript::class.java, "TalkScript")
        ConfigurationSerialization.registerClass(Dialog::class.java, "Dialog")
    }

    override fun setupSchedulers() {
    }

}