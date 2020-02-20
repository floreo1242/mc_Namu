package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.message.InventoryMessage
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandAddStuff(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        val itemInMainHand = player.inventory.itemInMainHand

        if (itemInMainHand.type == Material.AIR) {
            player.sendErrorMessage(InventoryMessage.NOT_FOUND_ITEM_IN_HAND)
            return true
        }


        shop.addStuff(itemInMainHand, args.nextInt(0))
        ShopRepository.editShop(shop)
        player.sendInfoMessage(ShopMessage.ADD_SHOP_STUFF)
        return true
    }
}