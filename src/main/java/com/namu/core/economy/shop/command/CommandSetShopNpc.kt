package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.shop.model.ShopMessage
import com.namu.core.economy.shop.model.ShopRepository
import com.namu.core.economy.shop.model.entity.Shop
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandSetShopNpc : ShopCommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점의 NPC를 설정합니다."

    override val usage: String = "<ShopCode> <NpcName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        shop.npcName = args.join(" ")
        ShopRepository.editShop(shop)
        player.sendInfoMessage(ShopMessage.SET_SHOP_NPC)
        return true
    }
}