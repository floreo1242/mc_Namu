package com.kkomi.treeisland.plugin.talkscript.commands

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptRepository
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TalkScriptCreateCommand(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val name = args.next()

        if (TalkScriptRepository.getTalkScript(name) != null) {
            sender.sendErrorMessage(TalkScriptMessage.ALREADY_EXIST_NAME)
            return true
        }

        TalkScriptRepository.addTalkScript(TalkScript(name, mutableListOf(), ""))
        sender.sendInfoMessage(TalkScriptMessage.CREATE_TALK_SCRIPT)
        return true
    }
}