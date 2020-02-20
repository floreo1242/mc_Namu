package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.inventory.GuildInfoInventory
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandGuildInfo : GuildCommandComponent() {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        GuildInfoInventory(player, guild).open()
        return true
    }
}