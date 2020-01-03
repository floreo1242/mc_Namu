package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.inventory.GuildInfoInventory
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandGuildInfo(usage: String, description: String, argumentsLength: Int) : GuildCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        GuildInfoInventory(player, guild).open()
        return true
    }
}