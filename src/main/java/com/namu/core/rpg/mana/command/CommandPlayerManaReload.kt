package com.kkomi.mana.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.mana.model.PlayerManaRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandPlayerManaReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(
            sender: CommandSender,
            label: String,
            command: Command,
            componentLabel: String,
            args: ArgumentList
    ): Boolean {
        PlayerManaRepository.reloadPlayerMana()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}