package com.kkomi.treeisland.quest.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.quest.inventory.QuestRewardInventory
import com.kkomi.treeisland.quest.model.Quest
import com.kkomi.treeisland.quest.model.QuestType
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestRewardItems(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        QuestRewardInventory(player, quest).open()
        return true
    }

}