package com.kkomi.treeisland.plugin.integration.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.TargetCommandComponent
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.entity.Player

open class TargetPlayerInfoCommandComponent(usage: String, description: String, argumentsLength: Int) : TargetCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        return onCommand(PlayerInfo(sender), OfflinePlayerInfo(target), label, command, componentLabel, args)
    }

    open fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean = false
}