package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import com.kkomi.treeisland.plugin.quest.model.entity.QuestType
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetQuestType(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(commandSender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val questType = getQuestType(args.next())
        if (questType == null) {
            commandSender.sendErrorMessage("유효한 퀘스트 타입이 아닙니다.")
            return true
        }
        quest.type = questType
        QuestRepository.editQuest(quest)
        commandSender.sendInfoMessage(QuestMessage.QUEST_SET_TYPE)
        return true
    }

    private fun getQuestType(value: String): QuestType? {
        return try {
            QuestType.valueOf(value)
        } catch (exception: Exception) {
            null
        }
    }

}