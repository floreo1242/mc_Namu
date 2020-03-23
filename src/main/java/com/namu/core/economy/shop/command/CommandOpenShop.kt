package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.namu.core.economy.shop.model.entity.Shop
import com.namu.core.economy.shop.inventory.ShopInventory
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandOpenShop : ShopCommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "상점을 열어봅니다."

    override val usage: String = "<ShopCode>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, shop: Shop): Boolean {
        ShopInventory(player, shop).open()
        return true
    }
}