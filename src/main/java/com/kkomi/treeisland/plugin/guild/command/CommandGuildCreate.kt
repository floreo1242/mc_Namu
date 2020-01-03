package com.kkomi.treeisland.plugin.guild.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMember
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandGuildCreate(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val guildName = args.next()
        val guild = GuildRepository.getGuild(guildName)
        val player = sender as Player

        if (GuildRepository.getGuildList().flatMap { it.members }.map { it.uuid }.find { it == player.uniqueId.toString() } != null) {
            player.sendErrorMessage("이미 길드에 가입되어 있습니다.")
            return true
        }

        if (guild != null) {
            player.sendErrorMessage("이미 존재하는 길드입니다.")
            return true
        }

        GuildRepository.addGuild(Guild(guildName, 1, listOf(GuildMember(player.uniqueId.toString(), GuildGrade.MANAGER))))
        return true
    }
}