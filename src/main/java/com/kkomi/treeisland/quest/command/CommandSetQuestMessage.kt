package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestMessage(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        when (args.next()) {
            "dispose" -> CommandSetQuestDisposeMessage(usage, description, argumentsLength).onCommand(player, label, command, componentLabel, args, quest)
            "complete" -> CommandSetQuestCompleteMessage(usage, description, argumentsLength).onCommand(player, label, command, componentLabel, args, quest)
            "purpose" -> CommandSetQuestPurposeMessage(usage, description, argumentsLength).onCommand(player, label, command, componentLabel, args, quest)
            "accept" -> CommandSetQuestAcceptMessage(usage, description, argumentsLength).onCommand(player, label, command, componentLabel, args, quest)
            else -> return false
        }
        return true
    }

}