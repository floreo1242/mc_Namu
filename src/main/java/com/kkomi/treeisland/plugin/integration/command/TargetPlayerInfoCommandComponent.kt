package com.kkomi.treeisland.plugin.integration.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.TargetCommandComponent
import com.kkomi.treeisland.plugin.integration.model.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.model.PlayerInfo
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.entity.Player

abstract class TargetPlayerInfoCommandComponent : TargetCommandComponent() {

    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        return onCommand(PlayerInfo(sender), OfflinePlayerInfo(target), label, command, componentLabel, args)
    }

    open fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean = false
}