package com.kkomi.treeisland.plugin.itemdb.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandEquipmentItemCreate(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipmentItemRepository.createItem(args.next())
        return true
    }
}