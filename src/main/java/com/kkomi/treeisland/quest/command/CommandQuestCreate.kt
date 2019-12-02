package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.QuestPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandQuestCreate(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val questName = args.next()
        QuestPlugin.questManager.createQuest(questName)
        (sender as Player).sendInfoMessage("퀘스트를 생성하였습니다.")
        return true
    }

}