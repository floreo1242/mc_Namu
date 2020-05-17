package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.shop.model.ShopMessage
import com.namu.core.economy.shop.model.repo.ShopRepository
import com.namu.core.economy.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandRemoveShop : ShopCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "상점을 삭제합니다."

    override val usage: String = "<ShopCode>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        ShopRepository.removeShop(shop.name)
        player.sendInfoMessage(ShopMessage.DELETE_SHOP)
        return true
    }
}