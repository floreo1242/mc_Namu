package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandDisposeGuildInvite : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "길드 초대를 거절합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player

        val request = GuildInviteRequestRepository.get(player)
        if (request == null) {
            player.sendErrorMessage("길드에 초대된 요청이 없습니다.")
            return true
        }

        GuildInviteRequestRepository.removeTo(request.to)
        if (request.from.isOnline) {
            request.from.sendInfoMessage("${request.to.name}님이 길드 초대를 거절하셨습니다.")
        }
        return true
    }
}