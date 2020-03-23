package com.namu.core.rpg.stat.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.rpg.stat.inventory.PlayerStatInventory
import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.StatConfigRepository
import com.namu.core.rpg.stat.model.entity.StatConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandReloadPlayerStat : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "스텟 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerStatRepository.reloadPlayerStat()
        StatConfigRepository.reloadStatConfig()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}