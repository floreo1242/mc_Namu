package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.plugin.money.MoneyPlugin
import com.kkomi.treeisland.plugin.money.entity.PlayerMoney
import org.bukkit.OfflinePlayer

class OfflinePlayerInfo(
        val offlinePlayer: OfflinePlayer
) {
    private val uuid: String = offlinePlayer.uniqueId.toString()
    val moneyInfo: PlayerMoney
        get() {
            return MoneyPlugin.moneyManager.getPlayerMoney(uuid)!!
        }
}