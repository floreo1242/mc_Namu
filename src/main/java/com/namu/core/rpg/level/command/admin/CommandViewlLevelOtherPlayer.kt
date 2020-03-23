package com.namu.core.rpg.level.command.admin

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.TargetCommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.level.model.LevelConfigRepository
import com.namu.core.rpg.level.util.playerLevel
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandViewlLevelOtherPlayer : TargetCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "해당 플레이어의 경험치 상태를 봅니다."

    override val usage: String = "<PlayerName>"

    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val levelInfo = target.playerLevel!!
        sender.sendInfoMessage("%s님의 정보".format(target))
        sender.sendInfoMessage("Level : %s".format(levelInfo.level))
        sender.sendInfoMessage("Exp : %d / %d".format(levelInfo.exp, LevelConfigRepository.getLevelConfig().getExpByLevel(levelInfo.level)))
        return true
    }

}