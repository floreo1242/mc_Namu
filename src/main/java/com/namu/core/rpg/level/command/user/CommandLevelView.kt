package com.namu.core.rpg.level.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.level.model.LevelConfigRepository
import com.namu.core.rpg.level.util.playerLevel
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandLevelView : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어의 레벨 상태를 확인합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val levelInfo = (sender as Player).playerLevel
        sender.sendInfoMessage("Level : %s".format(levelInfo.level))
        sender.sendInfoMessage("Exp : %d / %d".format(levelInfo.exp, LevelConfigRepository.getLevelConfig().getExpByLevel(levelInfo.level)))
        return true
    }
}