package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.plugin.money.model.MoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import org.bukkit.OfflinePlayer

class OfflinePlayerInfo(
        val offlinePlayer: OfflinePlayer
) {
    private val uuid: String = offlinePlayer.uniqueId.toString()
    val moneyInfo: PlayerMoney
        get() {
            return MoneyRepository.getPlayerMoney(uuid)!!
        }
}