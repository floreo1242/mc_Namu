package com.kkomi.treeisland.plugin.bag.command

import com.kkomi.treeisland.plugin.bag.model.BagRepository
import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandBagReload : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "가방 데이터를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        BagRepository.reloadBag()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}