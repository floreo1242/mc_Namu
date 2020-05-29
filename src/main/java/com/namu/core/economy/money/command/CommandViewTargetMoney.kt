package com.namu.core.economy.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.TargetCommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.money.model.MoneyMessage
import com.namu.core.economy.money.util.playerMoney
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.entity.Player
import java.text.DecimalFormat

class CommandViewTargetMoney : TargetCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "플레이어의 돈을 확인합니다."

    override val usage: String = "<PlayerName>"

    override fun onCommand(sender: Player, target: OfflinePlayer, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        sender.sendInfoMessage(MoneyMessage.TAKE_TARGET_MONEY.format(target.name, DecimalFormat("#,##0").format(target.playerMoney?.money ?: 0)))
        return true
    }

}