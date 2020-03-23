package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.namu.core.economy.shop.model.ShopRepository
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandReloadShop : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "상정 정보를 리로드 합니다."

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        ShopRepository.reloadShop()
        sender.sendDebugMessage("데이터를 리로드 하였습니다.")
        return true
    }
}