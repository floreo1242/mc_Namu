package com.kkomi.treeisland.plugin.money.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.OfflinePlayerInfo
import com.kkomi.treeisland.plugin.integration.PlayerInfo
import com.kkomi.treeisland.plugin.integration.command.TargetPlayerInfoCommandComponent
import com.kkomi.treeisland.plugin.money.model.MoneyMessage
import org.bukkit.command.Command
import java.text.DecimalFormat

class CommandViewTargetMoney(usage: String, description: String, argumentsLength: Int) : TargetPlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(sender: PlayerInfo, target: OfflinePlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        sender.player.sendInfoMessage(MoneyMessage.TAKE_TARGET_MONEY.format(target.offlinePlayer.name,
                DecimalFormat("#,##0").format(target.moneyInfo.money)))
        return true
    }
}