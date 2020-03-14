package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandCreateShop : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "상점을 생성합니다."

    override val usage: String = "<ShopCode>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val shopName = args.next()
        if (ShopRepository.getShop(shopName) != null) {
            sender.sendErrorMessage(ShopMessage.ALREADY_SHOP)
            return true
        }
        ShopRepository.createShop(Shop(shopName, mutableListOf(), ""))
        (sender as Player).sendInfoMessage(ShopMessage.CREATE_SHOP)
        return true
    }
}