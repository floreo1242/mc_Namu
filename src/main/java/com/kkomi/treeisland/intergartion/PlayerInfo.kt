package com.kkomi.treeisland.intergartion

import com.kkomi.treeisland.money.MoneyPlugin
import com.kkomi.treeisland.money.entity.PlayerMoney
import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid : String = player.uniqueId.toString()
    val moneyInfo : PlayerMoney = MoneyPlugin.moneyManager.getPlayerMoney(uuid)!!
}