package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.shop.model.SellShopRepository
import com.namu.core.economy.shop.model.ShopMessage
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandAddSellStuff : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "판매상점에 아이템을 등록합니다."

    override val usage: String = "<Price>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val amount = args.nextInt()
        val itemStack = (sender as Player).inventory.itemInMainHand
        val sellShop = SellShopRepository.getSellShop()

        if (itemStack.type == Material.AIR) {
            sender.sendErrorMessage("손에 아이템이 없습니다!")
            return true
        }

        sellShop.addStuff(itemStack, amount)
        SellShopRepository.editSellShop(sellShop)
        sender.sendInfoMessage(ShopMessage.ADD_KEYWORD_SHOP_STUFF)
        return true
    }

}