package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.inventory.GuildInfoInventory
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.util.playerGuild
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandViewGuildInfo : GuildCommandComponent() {
    override val argumentsLength: Int = 0

    override val description: String = "해당 길드의 정보를 확인합니다."

    override val usage: String = "<GuildName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {

        val findGuild = if (args.remain() == 1) {

            args.next()

        }else {
            val playerGuild = player.playerGuild

            if (playerGuild.guildName == "") {
                player.sendErrorMessage("소속되어 있는 길드가 없습니다.")
                return true
            }

            playerGuild.guildName
        }

        GuildRepository.getGuild(findGuild)?.let { GuildInfoInventory(player, it).open() }
        return true
    }
}