package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import com.kkomi.treeisland.plugin.shop.model.KeywordShopRepository
import com.kkomi.treeisland.plugin.shop.model.ShopMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandRemoveKeywordStuff : CommandComponent() {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() != 1) {
            sender.sendMessage("/shopa §6keyword §fremove [keyword]")
            return true
        }
        val keyword = args.join(" ")
        val keywordShop = KeywordShopRepository.getKeywordShop()
        val keywordStuff = keywordShop.stuffList.find { it.keyword == keyword }
        if (keywordStuff == null) {
            (sender as Player).sendErrorMessage("찾을 수 없는 키워드 입니다.")
            return true
        }

        keywordShop.removeStuff(keywordStuff)
        KeywordShopRepository.editKeywordShop(keywordShop)
        (sender as Player).sendInfoMessage(ShopMessage.REMOVE_KEYWORD_SHOP_STUFF)
        return true
    }
}