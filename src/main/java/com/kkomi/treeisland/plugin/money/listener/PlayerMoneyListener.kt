package com.kkomi.treeisland.plugin.money.listener

import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerMoneyListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        val playerMoney = PlayerMoneyRepository.getPlayerMoney(player.uniqueId.toString())

        if (playerMoney == null) {
            PlayerMoneyRepository.addPlayerMoney(PlayerMoney(player.uniqueId.toString(), 0))
            return
        }
    }
}