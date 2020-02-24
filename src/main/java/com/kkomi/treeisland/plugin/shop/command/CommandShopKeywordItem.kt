package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandShopKeywordItem : CommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점의 키워드를 설정합니다."

    override val usage: String = "(add/remove) <Keyword> <Price>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() < 2) {
            return false
        }
        return when (args.next()) {
            "add" -> CommandAddKeywordStuff().onCommand(sender, label, command, componentLabel, args)
            "remove" -> CommandRemoveKeywordStuff().onCommand(sender, label, command, componentLabel, args)
            else -> false
        }
    }
}