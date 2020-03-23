package com.namu.core.economy.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.economy.money.model.PlayerMoneyRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandReloadPlayerMoney : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "플레이어 돈 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        PlayerMoneyRepository.reloadPlayerMoney()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}