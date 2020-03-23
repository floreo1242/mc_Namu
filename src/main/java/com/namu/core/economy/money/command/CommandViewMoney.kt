package com.namu.core.economy.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.money.model.MoneyMessage
import com.namu.core.economy.money.util.playerMoney
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.text.DecimalFormat

class CommandViewMoney : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "자신의 돈을 확인합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val player = sender as Player
        player.sendInfoMessage(MoneyMessage.VIEW_MONEY.format(DecimalFormat("#,##0").format(player.playerMoney.money)))
        return true
    }
}