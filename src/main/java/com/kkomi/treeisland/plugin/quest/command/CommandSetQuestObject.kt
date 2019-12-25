package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetQuestObject(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(commandSender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        when (args.next()) {
            "string" -> CommandSetQuestObjectString(usage, description, argumentsLength).onCommand(commandSender, label, command, componentLabel, args, quest)
            "location" -> CommandSetQuestObjectLocation(usage, description, argumentsLength).onCommand(commandSender, label, command, componentLabel, args, quest)
            "itemstack" -> CommandSetQuestObjectItemStack(usage, description, argumentsLength).onCommand(commandSender, label, command, componentLabel, args, quest)
        }
        return true
    }

}