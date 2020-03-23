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

class CommandGiveMoney : TargetCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "플레이어에게 돈을 지급합니다."

    override val usage: String = "<PlayerName> <Money>"

    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val money = args.nextLong(0)

        target.playerMoney!!.apply {
            this.money = money
        }.edit()

        sender.sendInfoMessage(MoneyMessage.GIVE_MONEY)
        return true
    }
}