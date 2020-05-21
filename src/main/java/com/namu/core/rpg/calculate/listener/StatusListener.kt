package com.namu.core.rpg.calculate.listener

import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.stat.api.PlayerStatUpdateEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class StatusListener : Listener {

    @EventHandler
    fun onPlayerStatUpdateEvent(event: PlayerStatUpdateEvent) {
        PlayerStatusRepository.getPlayerStatus(event.player).run {
            calculate()
            apply()
        }
    }

}