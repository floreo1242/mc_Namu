package com.kkomi.treeisland.plugin.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.model.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.PlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.money.model.MoneyMessage
import org.bukkit.command.Command
import java.text.DecimalFormat

class CommandViewMoney : PlayerInfoCommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "자신의 돈을 확인합니다."

    override val usage: String = ""

    override fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        playerInfo.player.sendInfoMessage(MoneyMessage.VIEW_MONEY.format(DecimalFormat("#,##0").format(playerInfo.moneyInfo.money)))
        return true
    }
}