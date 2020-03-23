package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.itemdb.model.CustomItemRepository
import com.namu.core.utility.itemdb.model.entity.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGetCustomItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "create custom item"

    override val usage: String = "<code>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        (sender as Player).inventory.addItem(CustomItemRepository.getCustomItem(args.next())?.toItemStack())
        return true
    }

}