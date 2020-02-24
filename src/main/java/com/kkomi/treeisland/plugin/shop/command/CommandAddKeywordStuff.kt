package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.KeywordShopRepository
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandAddKeywordStuff : CommandComponent() {

    override val argumentsLength: Int = 2

    override val description: String = "상점에 키워드를 등록합니다."

    override val usage: String = "<ShopCode> <Keyword>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() != 2) {
            return false
        }
        val amount = args.nextInt()
        val keyword = args.join(" ")
        val keywordShop = KeywordShopRepository.getKeywordShop()

        keywordShop.addStuff(keyword, amount)
        KeywordShopRepository.editKeywordShop(keywordShop)
        (sender as Player).sendInfoMessage(ShopMessage.ADD_KEYWORD_SHOP_STUFF)
        return true
    }
}