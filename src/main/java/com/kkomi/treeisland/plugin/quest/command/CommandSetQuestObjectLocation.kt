package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.quest.model.Quest
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestObjectLocation(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        quest.locationObject = player.location
        quest.save()
        player.sendInfoMessage("목표 위치를 설정하였습니다.")
        return true
    }

}