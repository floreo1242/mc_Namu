package com.kkomi.treeisland.plugin.level.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.PlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import org.bukkit.command.Command

class CommandLevelView : PlayerInfoCommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어의 레벨 상태를 확인합니다."

    override val usage: String = ""

    override fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val levelInfo = playerInfo.levelInfo
        playerInfo.sendInfoMessage("Level : %s".format(levelInfo.level))
        playerInfo.sendInfoMessage("Exp : %d / %d".format(levelInfo.exp, LevelConfigRepository.getLevelConfig().getExpByLevel(levelInfo.level)))
        return true
    }
}