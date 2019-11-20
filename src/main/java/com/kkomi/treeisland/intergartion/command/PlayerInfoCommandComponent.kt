package com.kkomi.treeisland.intergartion.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.treeisland.intergartion.PlayerInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class PlayerInfoCommandComponent(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        return onCommand(PlayerInfo(sender as Player),label, command, componentLabel, args)
    }

    abstract fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean
}