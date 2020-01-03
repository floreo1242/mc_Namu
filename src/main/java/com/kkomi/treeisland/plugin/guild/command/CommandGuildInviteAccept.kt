package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.model.GuildInviteRequestRepository
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class CommandGuildInviteAccept(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player

        val request = GuildInviteRequestRepository.get(player)
        if (request == null) {
            player.sendErrorMessage("길드에 초대된 요청이 없습니다.")
            return true
        }

        val guild = request.guild

        // Add member in guild
        guild.members[request.to.uniqueId.toString()] = GuildMemberState(GuildGrade.MEMBER, 0)
        guild.members
                .map { Bukkit.getOfflinePlayer(UUID.fromString(it.key)) }
                .filter { it.isOnline }
                .forEach { it.player.sendInfoMessage("${request.to.name}님이 길드에 초대되었습니다.") }
        GuildRepository.editGuild(guild)

        // Remove invite request from to
        GuildInviteRequestRepository.removeTo(player)

        // Edit player current guild name
        PlayerGuildRepository.editPlayerGuild(player.getPlayerInfo().guildInfo.apply { guildName = guild.name })

        player.sendInfoMessage("길드 초대를 수락하셨습니다.")
        return true
    }
}