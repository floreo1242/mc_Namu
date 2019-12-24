package com.kkomi.treeisland.plugin.talkscript.commands

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptRepository
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TalkScriptRemoveDialogCommand(usage: String, description: String, argumentsLength: Int) : TalkScriptCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, talkScript: TalkScript): Boolean {
        val index = args.nextInt()
        if (talkScript.dialogList.size <= index) {
            sender.sendErrorMessage(TalkScriptMessage.INCORRECT_INDEX)
            return true
        }
        talkScript.dialogList.removeAt(index)
        TalkScriptRepository.editTalkScript(talkScript)
        sender.sendInfoMessage(TalkScriptMessage.REMOVE_TALK_DIALOG)
        return true
    }
}