package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.PlayerGuildRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildGrade
import com.namu.core.rpg.guild.util.playerGuild
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandKickGuildMemeber : GuildCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "해당 유저를 길드에서 추방시킵니다."

    override val usage: String = "<PlayerName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val targetName = args.next()
        val target = Bukkit.getOfflinePlayer(targetName)

        if (guild.members[player.uniqueId.toString()]!!.grade == GuildGrade.DEPUTY_MANAGER) {
            player.sendErrorMessage("길드 관리자가 아닙니다.")
            return true
        }

        if (player.playerGuild.guildName != target.playerGuild?.guildName) {
            player.sendErrorMessage("길드원이 아닙니다.")
            return true
        }

        guild.members.remove(target.uniqueId.toString())
        GuildRepository.editGuild(guild)
        PlayerGuildRepository.editPlayerGuild(target.playerGuild!!.apply { guildName = "" })

        player.sendInfoMessage("해당 플레이어를 길드에서 추방하였습니다.")
        if (target.isOnline) {
            (target as Player).sendInfoMessage("길드에서 추방당하였습니다.")
        }
        return true
    }
}
