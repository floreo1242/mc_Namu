package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.quest.QuestPlugin
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandQuestReload(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        QuestRepository.reloadQuest()
        sender.sendDebugMessage(QuestMessage.QUEST_RELOAD)
        return true
    }

}