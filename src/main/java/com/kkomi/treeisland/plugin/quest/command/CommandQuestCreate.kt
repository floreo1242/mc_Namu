package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.monster.model.entity.DropItem
import com.kkomi.treeisland.plugin.monster.model.entity.DropItemType
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.*
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandQuestCreate(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val questName = args.next()
        val quest = QuestRepository.getQuest(questName)

        if (quest != null) {
            sender.sendErrorMessage(QuestMessage.QUEST_ALREADY_NAME)
            return true
        }

        QuestRepository.addQuest(
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
                        QuestReward(
                                listOf(
                                        QuestRewardItem(
                                                QuestRewardItemType.CONSUMPTION_ITEM,
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