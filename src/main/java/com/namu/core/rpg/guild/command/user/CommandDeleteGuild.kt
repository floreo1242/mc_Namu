package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildGrade
import com.namu.core.rpg.guild.util.playerGuild
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandDeleteGuild : GuildCommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "길드를 삭제합니다."

    override val usage: String = ""

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {

        if (guild.members[player.uniqueId.toString()]!!.grade == GuildGrade.DEPUTY_MANAGER) {
            player.sendErrorMessage("길드 관리자가 아닙니다.")
            return true
        }

        if (guild.members.size != 1) {
            player.sendInfoMessage("길드원을 모두 강퇴 한 후 길드를 삭제해주세요")
            return true
        }

        GuildRepository.removeGuild(guild.name)
        player.playerGuild.guildName = ""

        player.sendInfoMessage("길드를 삭제하였습니다.")
        return true
    }
}