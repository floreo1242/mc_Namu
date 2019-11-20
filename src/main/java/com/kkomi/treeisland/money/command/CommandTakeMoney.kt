package com.kkomi.treeisland.money.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.integration.OfflinePlayerInfo
import com.kkomi.treeisland.integration.PlayerInfo
import com.kkomi.treeisland.integration.command.TargetPlayerInfoCommandComponent
import org.bukkit.command.Command

class CommandTakeMoney(usage: String, description: String, argumentsLength: Int) : TargetPlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextInt(0)
        target.moneyInfo.takeMoney(money)
        sender.player.sendInfoMessage("${target.offlinePlayer.name}의 돈을 차감하였습니다.")
        return true
    }
}