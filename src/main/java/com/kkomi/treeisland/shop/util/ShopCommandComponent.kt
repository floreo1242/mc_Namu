package com.kkomi.treeisland.shop.util

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.treeisland.shop.ShopPlugin
import com.kkomi.treeisland.shop.eneity.Shop
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class ShopCommandComponent(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        sender as Player
        val shop = ShopPlugin.shopManager.getShop(args.next())
        if (shop == null ) {
            sender.sendErrorMessage("상점을 찾을 수 없습니다.")
            return false
        }
        return onCommand(sender, label, command, componentLabel, args, shop)
    }

    abstract fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop) : Boolean

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, componentLabel: String, args: ArgumentList): Iterable<String> {
        return if (args.getCursor() == 1) {
            ShopPlugin.shopManager.shopNames
        } else {
            listOf()
        }

    }
}