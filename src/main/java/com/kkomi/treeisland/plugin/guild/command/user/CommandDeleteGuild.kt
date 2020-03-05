package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandDeleteGuild : GuildCommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "길드를 삭제합니다."

    override val usage: String = ""

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {

        if (guild.members.size != 1) {
            player.sendInfoMessage("길드원을 모두 강퇴 한 후 길드를 삭제해주세요")
            return true
        }

        GuildRepository.removeGuild(guild.name)
        player.getPlayerInfo().guildInfo.guildName = ""

        player.sendInfoMessage("길드를 삭제하였습니다.")
        return true
    }
}