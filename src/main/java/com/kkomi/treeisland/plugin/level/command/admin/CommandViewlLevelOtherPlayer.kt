package com.kkomi.treeisland.plugin.level.command.admin

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.TargetPlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import org.bukkit.command.Command

class CommandViewlLevelOtherPlayer : TargetPlayerInfoCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "해당 플레이어의 경험치 상태를 봅니다."

    override val usage: String = "<PlayerName>"

    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val levelInfo = target.levelInfo
        sender.sendInfoMessage("%s님의 정보".format(target))
        sender.sendInfoMessage("Level : %s".format(levelInfo.level))
        sender.sendInfoMessage("Exp : %d / %d".format(levelInfo.exp, LevelConfigRepository.getLevelConfig().getExpByLevel(levelInfo.level)))
        return true
    }

}