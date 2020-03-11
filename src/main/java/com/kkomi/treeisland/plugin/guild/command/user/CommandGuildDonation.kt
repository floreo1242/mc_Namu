package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGuildDonation : GuildCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "길드에 기부를 합니다."

    override val usage: String = "<Price>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val price = args.nextInt()
        guild.guildPoint += price
        guild.members[player.uniqueId.toString()] = (guild.members[player.uniqueId.toString()]
                ?: GuildMemberState(GuildGrade.MEMBER, 0)).apply { contribution += price }
        player.sendInfoMessage("해당 플레이어의 직급을 변경하였습니다.")
        return true
    }

}