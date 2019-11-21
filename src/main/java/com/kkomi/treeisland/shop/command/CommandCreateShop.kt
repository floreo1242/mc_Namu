package com.kkomi.treeisland.shop.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.command.CommandComponent
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.shop.ShopPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandCreateShop(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        ShopPlugin.shopManager.createShop(args.next())
        (sender as Player).sendInfoMessage("상점을 생성하였습니다.")
        return true
    }
}