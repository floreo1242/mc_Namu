package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.plugin.shop.eneity.Shop
import com.kkomi.treeisland.plugin.shop.inventory.ShopInventory
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandOpenShop(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        ShopInventory(player, shop).open()
        return true
    }
}