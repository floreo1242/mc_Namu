package com.kkomi.treeisland.plugin.equipitem.listener

import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class PlayerEquipItemListener : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerEquipItemRepository.getPlayerEquipItem(uuid) == null) {
            PlayerEquipItemRepository.createPlayerEquipItem(uuid)
        }

        if (event.player.inventory.getItem(0) == null) {
            event.player.inventory.setItem(0,ItemStack(Material.IRON_BARDING))
        }
    }
}