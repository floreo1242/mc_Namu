package com.kkomi.treeisland.money.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.intergartion.OfflinePlayerInfo
import com.kkomi.treeisland.intergartion.PlayerInfo
import com.kkomi.treeisland.intergartion.command.TargetPlayerInfoCommandComponent
import org.bukkit.command.Command

class CommandSetMoney(usage: String, description: String, argumentsLength: Int) : TargetPlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextInt(0)
        target.moneyInfo.money = money
        sender.player.sendInfoMessage("${target.offlinePlayer.name}의 돈을 설정하였습니다.")
        return true
    }
}