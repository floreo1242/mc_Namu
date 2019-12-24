package com.kkomi.treeisland.plugin.talkscript.commands

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import com.kkomi.treeisland.plugin.talkscript.inventory.TalkScriptInventory
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TalkScriptOpenCommand(usage: String, description: String, argumentsLength: Int) : TalkScriptCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, talkScript: TalkScript): Boolean {
        TalkScriptInventory(sender as Player, talkScript).open()
        return true
    }
}