package com.kkomi.treeisland.plugin.itemdb.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.entity.dummy.EquipmentDataDummy
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandEquipmentItemDebug(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipmentDataDummy.get(sender as Player)
        return true
    }
}