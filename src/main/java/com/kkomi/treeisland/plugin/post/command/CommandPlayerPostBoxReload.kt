package com.kkomi.treeisland.plugin.post.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.post.model.PlayerPostBoxRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandPlayerPostBoxReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Reload Player Post"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerPostBoxRepository.reloadPlayerPostBox()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}