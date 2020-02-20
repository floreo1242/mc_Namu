package com.kkomi.treeisland.plugin.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.PlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.money.model.MoneyMessage
import org.bukkit.command.Command
import java.text.DecimalFormat

class CommandViewMoney(usage: String, description: String, argumentsLength: Int) : PlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        playerInfo.player.sendInfoMessage(MoneyMessage.VIEW_MONEY.format(DecimalFormat("#,##0").format(playerInfo.moneyInfo.money)))
        return true
    }
}