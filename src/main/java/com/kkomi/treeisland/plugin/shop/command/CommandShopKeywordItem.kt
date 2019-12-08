package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class CommandShopKeywordItem(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() < 2) {
            return false
        }
        return when (args.next()) {
            "add" -> CommandAddKeywordStuff("[price] [keyword]", "공용 판매 상점에 아이템을 추가합니다.",0).onCommand(sender, label, command, componentLabel, args)
            "remove" -> CommandRemoveKeywordStuff("[keyword]", "공용 판매 상점에 아이템을 삭제합니다.",0).onCommand(sender, label, command, componentLabel, args)
            else -> false
        }
    }
}