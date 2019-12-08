package com.kkomi.treeisland.plugin.money.listener

import com.kkomi.treeisland.plugin.money.MoneyPlugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerMoneyListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val moneyManager = MoneyPlugin.moneyManager
        val player = event.player

        if (moneyManager.uuids.contains(player.uniqueId.toString())) {
            return
        }

        moneyManager.createPlayerMoney(player)
    }
}