package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.shop.model.ShopMessage
import com.namu.core.economy.shop.model.repo.ShopRepository
import com.namu.core.economy.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandRemoveStuff : ShopCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점 아이템을 삭제합니다."

    override val usage: String = "<ShopCode> <Index>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        val index = args.nextInt()
        shop.stuffList.removeAt(index)
        ShopRepository.editShop(shop)
        player.sendInfoMessage(ShopMessage.REMOVE_SHOP_STUFF)
        return true
    }
}