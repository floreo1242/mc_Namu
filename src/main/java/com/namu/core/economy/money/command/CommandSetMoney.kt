package com.namu.core.economy.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.TargetCommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.money.model.MoneyMessage
import com.namu.core.economy.money.util.edit
import com.namu.core.economy.money.util.playerMoney
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetMoney : TargetCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "플레이어의 돈을 설정합니다."

    override val usage: String = "<PlayerName> <Money>"

    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextLong(0)

        target.playerMoney!!.apply {
            this.money = money
        }.edit()

        sender.sendInfoMessage(MoneyMessage.SET_MONEY)
        return true
    }
}