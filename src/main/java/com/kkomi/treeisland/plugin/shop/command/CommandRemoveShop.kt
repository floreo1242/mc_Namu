package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.eneity.Shop
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandRemoveShop(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        shop.remove()
        player.sendInfoMessage("상점을 삭제하였습니다.")
        return true
    }
}