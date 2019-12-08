package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.eneity.Shop
import com.kkomi.treeisland.plugin.shop.util.ShopCommandComponent
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandAddStuff(usage: String, description: String, argumentsLength: Int) : ShopCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        val itemInMainHand = player.inventory.itemInMainHand

        if (itemInMainHand.type == Material.AIR) {
            player.sendErrorMessage("아이템을 손에 들고 명령어를 입력해주세요!")
            return true
        }

        shop.addStuff(itemInMainHand, args.nextInt(0))
        shop.save()
        player.sendInfoMessage("상점에 아이템을 추가하였습니다.")
        return true
    }
}