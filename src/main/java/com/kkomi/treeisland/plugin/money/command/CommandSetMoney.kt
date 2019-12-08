package com.kkomi.treeisland.plugin.money.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.TargetPlayerInfoCommandComponent
import org.bukkit.command.Command

class CommandSetMoney(usage: String, description: String, argumentsLength: Int) : TargetPlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextInt(0)
        target.moneyInfo.money = money
        sender.player.sendInfoMessage("${target.offlinePlayer.name}의 돈을 설정하였습니다.")
        return true
    }
}