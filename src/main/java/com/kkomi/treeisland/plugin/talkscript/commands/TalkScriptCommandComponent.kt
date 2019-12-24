package com.kkomi.treeisland.plugin.talkscript.commands

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptRepository
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class TalkScriptCommandComponent(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() < 1) {
            return false
        }
        val name = args.next()
        val talkScript = TalkScriptRepository.getTalkScript(name)

        if (talkScript == null) {
            sender.sendErrorMessage(TalkScriptMessage.UNKNOWN_NAME)
            return true
        }

        return onCommand(sender, label, command, componentLabel, args, talkScript)
    }

    abstract fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, talkScript: TalkScript): Boolean
}