package com.kkomi.treeisland.integration

import com.kkomi.library.extension.sendDebugMessage
import com.kkomi.library.extension.sendErrorMessage
import com.kkomi.library.extension.sendInfoMessage
import com.kkomi.treeisland.money.MoneyPlugin
import com.kkomi.treeisland.money.entity.PlayerMoney
import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid : String = player.uniqueId.toString()
    val moneyInfo : PlayerMoney = MoneyPlugin.moneyManager.getPlayerMoney(uuid)!!

    fun sendErrorMessage(message : Any) {
        player.sendErrorMessage(message)
    }
    fun sendInfoMessage(message : Any) {
        player.sendInfoMessage(message)
    }
    fun sendDebugMessage(message : Any) {
        player.sendDebugMessage(message)
    }
}