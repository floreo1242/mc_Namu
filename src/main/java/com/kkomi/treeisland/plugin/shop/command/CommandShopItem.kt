package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandShopItem(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        return when (args.next()) {
            "add" -> CommandAddStuff("[shopName] [price]", "상점에 아이템을 추가합니다.", 2).onCommand(player, label, command, componentLabel, args, shop)
            "remove" -> CommandRemoveStuff("[shopName] [index]", "상점에 아이템을 삭제합니다.", 2).onCommand(player, label, command, componentLabel, args, shop)
            else -> false
        }
    }
}