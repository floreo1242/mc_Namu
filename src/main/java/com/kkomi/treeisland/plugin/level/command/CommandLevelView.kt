package com.kkomi.treeisland.plugin.level.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.PlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.level.model.LevelTableRepository
import org.bukkit.command.Command

class CommandLevelView(usage: String, description: String, argumentsLength: Int) : PlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val levelInfo = playerInfo.levelInfo
        playerInfo.sendInfoMessage("Level : %s".format(levelInfo.level))
        playerInfo.sendInfoMessage("Exp : %d / %d".format(levelInfo.exp, LevelTableRepository.getLevelExp(levelInfo.level)))
        return true
    }
}