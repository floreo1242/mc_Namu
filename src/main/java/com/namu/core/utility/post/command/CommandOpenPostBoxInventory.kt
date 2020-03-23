package com.namu.core.utility.post.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.utility.post.inventory.PostBoxInventory
import com.namu.core.utility.post.util.playerPostBox
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenPostBoxInventory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "open post box inventory"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PostBoxInventory(sender as Player, sender.playerPostBox).open()
        return true
    }

}