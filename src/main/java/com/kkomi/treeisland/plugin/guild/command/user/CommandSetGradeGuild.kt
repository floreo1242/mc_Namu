package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetGradeGuild : GuildCommandComponent() {

    override val argumentsLength: Int = 3

    override val description: String = "유저의 길드 등급을 변경합니다."

    override val usage: String = "<GuildName> <PlayerName> <Grade>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val targetName = args.next()
        val targetPlayer = Bukkit.getPlayer(targetName)

        if (targetPlayer == null) {
            player.sendErrorMessage("해당 플레이어를 찾을 수 없습니다.")
            return true
        }

        if (player.getPlayerInfo().guildInfo.guildName != targetPlayer.getPlayerInfo().guildInfo.guildName) {
            player.sendErrorMessage("길드원이 아닙니다.")
            return true
        }

        guild.changeGrade(targetPlayer.uniqueId.toString(), GuildGrade.valueOf(args.next()))
        GuildRepository.editGuild(guild)
        player.sendInfoMessage("해당 플레이어의 직급을 변경하였습니다.")
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, componentLabel: String, args: ArgumentList): Iterable<String> {
        if (args.getCursor() == 1) {
            return GuildGrade.values().map { it.toString() }
        }
        return super.onTabComplete(sender, command, label, componentLabel, args)
    }

}