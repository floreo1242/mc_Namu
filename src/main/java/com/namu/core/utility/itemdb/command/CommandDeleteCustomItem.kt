package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.utility.itemdb.model.CustomItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandDeleteCustomItem : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "create custom item"

    override val usage: String = "<code>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        CustomItemRepository.removeCustomItem(args.next())
        return true
    }

}