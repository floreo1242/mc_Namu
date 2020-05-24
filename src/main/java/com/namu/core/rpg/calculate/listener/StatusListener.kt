package com.namu.core.rpg.calculate.listener

import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.stat.api.PlayerStatUpdateEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent

class StatusListener : Listener {

    @EventHandler
    fun onPlayerStatUpdateEvent(event: PlayerStatUpdateEvent) {
        PlayerStatusRepository.getPlayerStatus(event.player).run {
            calculate()
            apply()
        }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        println(event.view.title)

//        if (event.clickedInventory != event.whoClicked.inventory) {
//            return
//        }

        if (event.slot == 1) {
            event.isCancelled = true
        }
    }

}