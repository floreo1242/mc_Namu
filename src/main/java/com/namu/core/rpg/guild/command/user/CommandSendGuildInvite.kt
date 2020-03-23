package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.MainCore
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.model.GuildInviteRequestRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildGrade
import com.namu.core.rpg.guild.util.playerGuild
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSendGuildInvite : GuildCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "길드에 플레이어를 초대합니다."

    override val usage: String = "<PlayerName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val target = args.next()
        val targetPlayer = Bukkit.getPlayer(target)

        if (guild.members[player.uniqueId.toString()]!!.grade == GuildGrade.DEPUTY_MANAGER) {
            player.sendErrorMessage("길드 관리자가 아닙니다.")
            return true
        }

        if (targetPlayer == null) {
            player.sendErrorMessage("해당 플레이어를 찾을 수 없습니다.")
            return true
        }

        if (targetPlayer.playerGuild.guildName.isNotEmpty()) {
            player.sendErrorMessage("이미 길드가 존재하는 플레이어 입니다.")
            return true
        }

        if (GuildInviteRequestRepository.get(targetPlayer) != null) {
            player.sendErrorMessage("이미 다른 길드의 요청을 처리중입니다.")
            return true
        }

        GuildInviteRequestRepository.create(player, targetPlayer, guild)
        player.sendInfoMessage("해당 플레이어에게 길드 초대를 보냈습니다. ( 10초 뒤 자동으로 거절됩니다. )")
        targetPlayer.sendInfoMessage("${guild.name} 길드에서 초대요청이 들어왔습니다.")
        targetPlayer.sendInfoMessage("/guild accept - 수락")
        targetPlayer.sendInfoMessage("/guild dispose - 거절")

        Bukkit.getScheduler().runTaskLater(MainCore.instance, Runnable {
            if (GuildInviteRequestRepository.get(targetPlayer) != null) {
                GuildInviteRequestRepository.removeTo(targetPlayer)
                targetPlayer.sendInfoMessage("자동으로 거절되었습니다.")
                player.sendInfoMessage("상대방이 거절하였습니다.")
            }
        }, 10 * 20L)

        return true
    }
}