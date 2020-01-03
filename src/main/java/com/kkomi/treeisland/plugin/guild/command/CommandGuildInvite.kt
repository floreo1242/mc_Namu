package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandGuildInvite(usage: String, description: String, argumentsLength: Int) : GuildCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val target = args.next()
        val targetPlayer = Bukkit.getPlayer(target)

        if (targetPlayer == null) {
            player.sendErrorMessage("해당 플레이어를 찾을 수 없습니다.")
            return true
        }

        if (GuildInviteRequestRepository.get(targetPlayer) != null) {
            player.sendErrorMessage("이미 다른 길드의 요청을 처리중입니다.")
            return true
        }

        GuildInviteRequestRepository.add(player, targetPlayer, guild)
        player.sendInfoMessage("해당 플레이어에게 길드 초대를 보냈습니다.")
        targetPlayer.sendInfoMessage("${guild.name} 길드에서 초대요청이 들어왔습니다.")
        targetPlayer.sendInfoMessage("/guild accept - 수락")
        targetPlayer.sendInfoMessage("/guild dispose - 거절")

        Bukkit.getScheduler().runTaskLater(Treeisland.instance, {
            if (GuildInviteRequestRepository.get(targetPlayer) != null) {
                GuildInviteRequestRepository.removeTo(targetPlayer)
            }
        }, 10 * 20L)

        return true
    }
}