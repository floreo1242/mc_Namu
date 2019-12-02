package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.model.Quest
import com.kkomi.treeisland.quest.model.QuestType
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestType(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val questType = getQuestType(args.next())
        if (questType == null) {
            player.sendErrorMessage("유효한 퀘스트 타입이 아닙니다.")
            return true
        }
        quest.type = questType
        quest.save()
        player.sendInfoMessage("퀘스트 타입을 설정하였습니다.")
        return true
    }

    private fun getQuestType(value : String) : QuestType? {
        return try {
            QuestType.valueOf(value)
        } catch (exception: Exception) {
            null
        }
    }

}