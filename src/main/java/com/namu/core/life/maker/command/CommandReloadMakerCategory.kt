package com.namu.core.life.maker.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.life.maker.model.MakerCategoryRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadMakerCategory : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "reload recipe"

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        MakerCategoryRepository.reloadMakerCategory()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}