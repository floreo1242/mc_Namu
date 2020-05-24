package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildGrade
import com.namu.core.rpg.guild.model.entity.GuildMemberState
import org.bukkit.command.Command
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
        GuildRepository.saveGuild(guild)
        player.sendInfoMessage("길드에 기부를 하였습니다.")
        return true
    }

}