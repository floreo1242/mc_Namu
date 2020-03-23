package com.namu.core.utility.subsystem.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.utility.subsystem.inventory.BlackSmithInventory
import com.namu.core.utility.subsystem.inventory.SculptureInventory
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenBlackSmithInventory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "제련 인벤토리를 엽니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        BlackSmithInventory(sender as Player).open()
        return true
    }

}