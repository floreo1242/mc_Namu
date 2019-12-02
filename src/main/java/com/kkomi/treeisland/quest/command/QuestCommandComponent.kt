package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.treeisland.quest.QuestPlugin
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class QuestCommandComponent(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val questName = args.next()
        val quest = QuestPlugin.questManager.getQuest(questName)
        if (quest == null) {
            (sender as Player).sendErrorMessage("존재하지 않는 퀘스트 입니다.")
            return true
        }
        return onCommand(sender as Player, label, command, componentLabel, args, quest)
    }

    abstract fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean
}