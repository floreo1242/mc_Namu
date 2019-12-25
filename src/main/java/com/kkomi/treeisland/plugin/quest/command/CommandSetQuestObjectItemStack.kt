package com.kkomi.treeisland.plugin.quest.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.library.message.InventoryMessage
import com.kkomi.treeisland.plugin.quest.model.QuestMessage
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetQuestObjectItemStack(usage: String, description: String, argumentsLength: Int) : QuestCommandComponent(usage, description, argumentsLength) {

    override fun onCommand(commandSender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList, quest: Quest): Boolean {
        val itemStack = (commandSender as Player).inventory.itemInMainHand
        if (itemStack == null) {
            commandSender.sendErrorMessage(InventoryMessage.NOT_FOUND_ITEM_IN_HAND)
            return true
        }
        quest.itemStackObject = itemStack
        QuestRepository.editQuest(quest)
        commandSender.sendInfoMessage(QuestMessage.QUEST_SET_OBJECT_ITEM_STACK)
        return true
    }

}