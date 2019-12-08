package com.kkomi.treeisland.plugin.integration.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class PlayerInfoCommandComponent(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        return onCommand(PlayerInfo(sender as Player),label, command, componentLabel, args)
    }

    abstract fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean
}