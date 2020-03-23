package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.itemdb.model.CustomItemRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandCustomItemReload() : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "Reload Custom Item"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        CustomItemRepository.reloadCustomItem()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}