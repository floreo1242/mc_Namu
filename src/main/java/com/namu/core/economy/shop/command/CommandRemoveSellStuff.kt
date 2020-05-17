package com.namu.core.economy.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.shop.model.repo.SellShopRepository
import com.namu.core.economy.shop.model.ShopMessage
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandRemoveSellStuff : CommandComponent() {

    override val argumentsLength: Int = 1

    override val description: String = "판매상점의 아이템을 삭제합니다."

    override val usage: String = ""


    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() != 1) {
            sender.sendMessage("/shopa §6sell §fremove [sell]")
            return true
        }
        val sell = args.join(" ")
        val sellShop = SellShopRepository.getSellShop()
        val itemStack = (sender as Player).inventory.itemInMainHand

        if (itemStack.type == Material.AIR) {
            sender.sendErrorMessage("손에 아이템이 없습니다!")
            return true
        }

        val sellStuff = SellShopRepository.getSellStuff(itemStack)

        if (sellStuff == null) {
            sender.sendErrorMessage("존재하지 않는 아이템 입니다.")
            return true
        }

        sellShop.removeStuff(sellStuff)
        SellShopRepository.editSellShop(sellShop)
        (sender as Player).sendInfoMessage(ShopMessage.REMOVE_KEYWORD_SHOP_STUFF)
        return true
    }
}