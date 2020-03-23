package com.namu.core.rpg.level.command.admin

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.level.model.LevelConfigRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadLevelConfig : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "레벨 콘피그 파일을 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        LevelConfigRepository.reloadLevelConfig()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }

}