package com.kkomi.treeisland.plugin.equipitem.listener

import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerEquipItemListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerEquipItemRepository.getPlayerEquipItem(uuid) == null) {
            PlayerEquipItemRepository.createPlayerEquipItem(uuid)
        }
    }
}