package com.kkomi.treeisland.money.command

import com.kkomi.library.command.ArgumentList
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.intergartion.OfflinePlayerInfo
import com.kkomi.treeisland.intergartion.PlayerInfo
import com.kkomi.treeisland.intergartion.command.PlayerInfoCommandComponent
import com.kkomi.treeisland.intergartion.command.TargetPlayerInfoCommandComponent
import org.bukkit.command.Command
import java.text.DecimalFormat

class CommandViewMoney(usage: String, description: String, argumentsLength: Int) : PlayerInfoCommandComponent(usage, description, argumentsLength) {
    override fun onCommand(playerInfo: PlayerInfo, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        playerInfo.player.sendInfoMessage("보유금액 : ${DecimalFormat("#,##0").format(playerInfo.moneyInfo.money)}")
        return true
    }
}