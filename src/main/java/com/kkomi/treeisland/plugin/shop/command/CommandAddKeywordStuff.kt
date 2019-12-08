package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandAddKeywordStuff(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        if (args.remain() != 2){
            sender.sendMessage("/shopa §6keyword §fadd [price] [keyword]")
            return true
        }
        val amount = args.nextInt()
        val keyword = args.join(" ")
        ShopPlugin.shopManager.keywordShop.addStuff(keyword, amount)
        ShopPlugin.shopManager.keywordShop.save()
        (sender as Player).sendInfoMessage("키워드를 추가하였습니다.")
        return true
    }
}