package com.namu.core.rpg.calculate.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandPlayerStatusCheck : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        (sender as Player).sendDebugMessage(PlayerStatusRepository.getPlayerStatus(sender))
        return true
    }
}