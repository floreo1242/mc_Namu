package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.namu.core.economy.shop.command.CommandAddSellStuff
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandShopSellItem : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "상점의 판매 아이템을 설정합니다."

    override val usage: String = "(add/remove) <Buy:Price>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        return when (args.next()) {
            "add" -> CommandAddSellStuff().onCommand(sender, label, command, componentLabel, args)
            "remove" -> CommandRemoveSellStuff().onCommand(sender, label, command, componentLabel, args)
            else -> false
        }
    }
}