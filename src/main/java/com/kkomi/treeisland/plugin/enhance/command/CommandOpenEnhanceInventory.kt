package com.kkomi.treeisland.plugin.enhance.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.treeisland.plugin.enhance.inventory.EnhanceInventory
import com.kkomi.treeisland.plugin.enhance.model.EnhanceStone
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenEnhanceInventory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Open enhance inventory"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        EnhanceInventory(sender as Player).open()
        return true
    }

}