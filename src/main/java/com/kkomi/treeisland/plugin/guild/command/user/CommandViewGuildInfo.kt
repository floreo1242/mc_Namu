package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.inventory.GuildInfoInventory
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandViewGuildInfo : GuildCommandComponent() {
    override val argumentsLength: Int = 1

    override val description: String = "해당 길드의 정보를 확인합니다."

    override val usage: String = "<GuildName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        GuildInfoInventory(player, guild).open()
        return true
    }
}