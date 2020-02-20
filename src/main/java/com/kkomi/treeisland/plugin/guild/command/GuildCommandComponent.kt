package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.TargetCommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class GuildCommandComponent : TargetCommandComponent() {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val playerInfo = (sender as Player).getPlayerInfo()

        if (playerInfo.guildInfo.guildName == "") {
            sender.sendErrorMessage("소속되어 있는 길드가 없습니다.")
            return true
        }
        return onCommand(sender, label, command, componentLabel, args, GuildRepository.getGuild(playerInfo.guildInfo.guildName)!!)
    }

    abstract fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean
}