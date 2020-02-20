package com.kkomi.treeisland.plugin.guild.command.admin

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandGuildReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Reload guild info"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        GuildRepository.reloadGuild()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }

}