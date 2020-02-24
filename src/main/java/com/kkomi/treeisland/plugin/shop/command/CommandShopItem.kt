package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandShopItem : ShopCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점의 아이템을 설정합니다."

    override val usage: String = "(add/remove) <Price/Index>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        return when (args.next()) {
            "add" -> CommandAddStuff().onCommand(player, label, command, componentLabel, args, shop)
            "remove" -> CommandRemoveStuff().onCommand(player, label, command, componentLabel, args, shop)
            else -> false
        }
    }

}