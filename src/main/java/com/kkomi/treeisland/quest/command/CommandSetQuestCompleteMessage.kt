package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.model.Quest
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestCompleteMessage(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val completeMessage = args.join(" ")
        quest.completeMessage = completeMessage
        quest.save()
        player.sendInfoMessage("완료 메시지를 설정하였습니다.")
        return true
    }

}