package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class ShopCommandComponent : CommandComponent() {

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        sender as Player
        val shop = ShopRepository.getShop(args.next())
        if (shop == null) {
            sender.sendErrorMessage(ShopMessage.UNKNOWN_NAME)
            return true
        }
        return onCommand(sender, label, command, componentLabel, args, shop)
    }

    abstract fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, componentLabel: String, args: ArgumentList): Iterable<String> {
        return if (args.getCursor() == 1) {
            ShopRepository.getShopList().map { it.name }
        } else {
            listOf()
        }

    }
}