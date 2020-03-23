package com.namu.core.rpg.stat.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.rpg.stat.inventory.PlayerStatInventory
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandOpenPlayerStatInventory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "스텟 정보를 확인 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerStatInventory(sender as Player).open()
        return true
    }
}