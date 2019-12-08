package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandRemoveKeywordStuff(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() != 1){
            sender.sendMessage("/shopa §6keyword §fremove [keyword]")
            return true
        }
        val keyword = args.join(" ")
        val keywordStuff = ShopPlugin.shopManager.keywordShop.stuffList.find { it.keyword == keyword }
        if (keywordStuff == null) {
            (sender as Player).sendErrorMessage("찾을 수 없는 키워드 입니다.")
            return true
        }
        ShopPlugin.shopManager.keywordShop.removeStuff(keywordStuff)
        ShopPlugin.shopManager.keywordShop.save()
        (sender as Player).sendInfoMessage("키워드를 삭제하였습니다.")
        return true
    }
}