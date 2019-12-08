package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.eneity.Shop
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetShopNpc(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        shop.npcName = args.join(" ")
        shop.save()
        player.sendInfoMessage("상점 담당 NPC 이름을 변경하였습니다.")
        return true
    }
}