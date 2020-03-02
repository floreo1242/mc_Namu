package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.guild.model.entity.GuildGrade
import com.kkomi.treeisland.plugin.guild.model.entity.GuildMemberState
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandCreateGuild : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "길드를 생성합니다."

    override val usage: String = "<GuildName>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val guildName = args.next()
        val guild = GuildRepository.getGuild(guildName)
        val player = sender as Player

        if (player.getPlayerInfo().guildInfo.guildName.isNotEmpty()) {
            player.sendErrorMessage("이미 길드에 가입되어 있습니다.")
            return true
        }

        if (guild != null) {
            player.sendErrorMessage("이미 존재하는 길드입니다.")
            return true
        }

        val createdGuild = Guild(guildName, 1, 0, mutableMapOf(player.uniqueId.toString() to GuildMemberState(GuildGrade.MANAGER, 0)))
        GuildRepository.addGuild(createdGuild)

        val playerGuild = player.getPlayerInfo().guildInfo
        playerGuild.guildName = guildName
        PlayerGuildRepository.editPlayerGuild(playerGuild)
        player.sendInfoMessage("&6${guildName} &f길드를 생성하였습니다.")
        return true
    }
}