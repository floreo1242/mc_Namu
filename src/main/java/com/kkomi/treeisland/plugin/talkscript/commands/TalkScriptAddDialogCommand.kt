package com.kkomi.treeisland.plugin.talkscript.commands

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptMessage
import com.kkomi.treeisland.plugin.talkscript.model.TalkScriptRepository
import com.kkomi.treeisland.plugin.talkscript.model.entity.Dialog
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class TalkScriptAddDialogCommand(usage: String, description: String, argumentsLength: Int) : TalkScriptCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, talkScript: TalkScript): Boolean {
        if (args.remain() <= 1) {
            return false
        }

        talkScript.dialogList.add(Dialog(args.next(), args.join(" ")))
        TalkScriptRepository.editTalkScript(talkScript)
        sender.sendInfoMessage(TalkScriptMessage.ADD_TALK_DIALOG)
        return true
    }
}