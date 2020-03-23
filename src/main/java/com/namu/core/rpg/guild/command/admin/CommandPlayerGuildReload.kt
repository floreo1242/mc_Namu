package com.namu.core.rpg.guild.command.admin

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.PlayerGuildRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandPlayerGuildReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어의 길드 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerGuildRepository.reloadPlayerGuild()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }

}