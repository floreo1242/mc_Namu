package com.kkomi.treeisland.plugin.equipitem.command.user

import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.equipitem.inventory.EquipItemInventory
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenEquipItem : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Open custom equip inventory"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EquipItemInventory(sender as Player).open()
        return true
    }

}