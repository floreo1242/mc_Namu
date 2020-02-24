package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.treeisland.Treeisland
import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSendGuildInvite : GuildCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "길드에 플레이어를 초대합니다."

    override val usage: String = "<GuildName> <PlayerName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val target = args.next()
        val targetPlayer = Bukkit.getPlayer(target)

        if (targetPlayer == null) {
            player.sendErrorMessage("해당 플레이어를 찾을 수 없습니다.")
            return true
        }

        if (targetPlayer.getPlayerInfo().guildInfo.guildName.isNotEmpty()) {
            player.sendErrorMessage("이미 길드가 존재하는 플레이어 입니다.")
            return true
        }

        if (GuildInviteRequestRepository.get(targetPlayer) != null) {
            player.sendErrorMessage("이미 다른 길드의 요청을 처리중입니다.")
            return true
        }

        GuildInviteRequestRepository.add(player, targetPlayer, guild)
        player.sendInfoMessage("해당 플레이어에게 길드 초대를 보냈습니다. ( 10초 뒤 자동으로 거절됩니다. )")
        targetPlayer.sendInfoMessage("${guild.name} 길드에서 초대요청이 들어왔습니다.")
        targetPlayer.sendInfoMessage("/guild accept - 수락")
        targetPlayer.sendInfoMessage("/guild dispose - 거절")

        Bukkit.getScheduler().runTaskLater(Treeisland.instance, {
            if (GuildInviteRequestRepository.get(targetPlayer) != null) {
                GuildInviteRequestRepository.removeTo(targetPlayer)
                targetPlayer.sendInfoMessage("자동으로 거절되었습니다.")
                player.sendInfoMessage("상대방이 거절하였습니다.")
            }
        }, 10 * 20L)

        return true
    }
}