package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGuildInviteDispose(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
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