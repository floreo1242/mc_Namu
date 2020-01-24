package com.kkomi.treeisland.plugin.level.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import com.kkomi.treeisland.plugin.level.model.entity.LevelConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandLevelConfigReload(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        LevelConfigRepository.reloadLevelConfig()
        sender.sendDebugMessage("데이터롤 리로드 하였습니다.")
        return true
    }

}