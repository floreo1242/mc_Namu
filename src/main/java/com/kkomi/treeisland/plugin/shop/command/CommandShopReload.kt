package com.kkomi.treeisland.plugin.shop.command

import com.kkomi.treeisland.library.command.ArgumentList
import com.kkomi.treeisland.library.command.CommandComponent
import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.shop.ShopPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandShopReload(usage: String, description: String, argumentsLength: Int) : CommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        ShopPlugin.shopManager.clear()
        Treeisland.shopPlugin.setupManagers()
        (sender as Player).sendDebugMessage("상점 플러그인을 리로드 하였습니다.")
        return true
    }
}