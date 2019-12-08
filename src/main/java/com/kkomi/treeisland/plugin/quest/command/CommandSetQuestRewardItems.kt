package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.plugin.quest.inventory.QuestRewardInventory
import com.kkomi.treeisland.plugin.quest.model.Quest
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetQuestRewardItems(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        QuestRewardInventory(player, quest).open()
        return true
    }

}