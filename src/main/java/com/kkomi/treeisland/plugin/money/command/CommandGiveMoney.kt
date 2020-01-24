package com.kkomi.treeisland.plugin.money.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.TargetPlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.money.model.MoneyMessage
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import org.bukkit.command.Command

class CommandGiveMoney(usage: String, description: String, argumentsLength: Int) : TargetPlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextLong(0)
        target.moneyInfo.giveMoney(money)
        PlayerMoneyRepository.editPlayerMoney(target.moneyInfo)
        sender.player.sendInfoMessage(MoneyMessage.GIVE_MONEY)
        return true
    }
}