package com.kkomi.treeisland.plugin.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.model.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.model.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.TargetPlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.money.model.MoneyMessage
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import org.bukkit.command.Command

class CommandGiveMoney : TargetPlayerInfoCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "플레이어에게 돈을 지급합니다."

    override val usage: String = "<PlayerName> <Money>"

    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextLong(0)
        target.moneyInfo.giveMoney(money)
        PlayerMoneyRepository.editPlayerMoney(target.moneyInfo)
        sender.player.sendInfoMessage(MoneyMessage.GIVE_MONEY)
        return true
    }
}