package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.inventory.InventoryMessage
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import com.kkomi.treeisland.plugin.shop.model.ShopRepository
import com.kkomi.treeisland.plugin.shop.model.entity.Shop
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandAddStuff : ShopCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점에 아이템을 등록합니다."

    override val usage: String = "<ShopCode> <Price>"

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