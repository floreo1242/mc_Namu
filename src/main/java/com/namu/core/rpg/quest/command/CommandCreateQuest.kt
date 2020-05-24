package com.namu.core.rpg.quest.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.quest.model.QuestMessage
import com.namu.core.rpg.quest.model.QuestRepository
import com.namu.core.rpg.quest.model.entity.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCreateQuest : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "퀘스트 양식을 생성합니다."

    override val usage: String = "<QuestCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val questName = args.next()
        val quest = QuestRepository.getQuest(questName)

        if (quest != null) {
            sender.sendErrorMessage(QuestMessage.QUEST_ALREADY_NAME)
            return true
        }

        QuestRepository.createQuest(
                Quest(
                        questName,
                        questName,
                        0,
                        QuestType.NORMAL,
                        "",
                        "",
                        "",
                        "",
                        listOf(
                                TalkScript(
                                        "talker",
                                        "message"
                                )
                        ),
                        "",
                        "",
                        "",
                        "",
                        listOf(
                                QuestObjective(
                                        QuestAction.FARMING_ITEM,
                                        4,
                                        "Apple"
                                )
                        ),
                        "",
                        "",
                        QuestReward(
                                listOf(
                                        QuestRewardItem(
                                                QuestRewardItemType.CUSTOM_ITEM,
                                                "",
                                                1
                                        )
                                ),
                                "",
                                0,
                                0
                        )
                )
        )
        sender.sendInfoMessage(QuestMessage.QUEST_CREATE)
        return true
    }

}