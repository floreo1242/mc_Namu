package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetQuestAction(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(commandSender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val action = getQuestAction(args.next())

        if (action == null) {
            commandSender.sendErrorMessage("존재하지 않는 퀘스트 조건입니다.")
            return true
        }

        quest.action = action
        QuestRepository.editQuest(quest)
        commandSender.sendInfoMessage(QuestMessage.QUEST_SET_ACTION)
        return true
    }

    private fun getQuestAction(questAction: String): QuestAction? {
        return try {
            QuestAction.valueOf(questAction)
        } catch (exception: Exception) {
            null
        }
    }

}