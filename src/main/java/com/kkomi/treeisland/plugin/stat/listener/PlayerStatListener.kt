package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.stat.model.AttackSpeedRepository
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*

class PlayerStatListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerStatRepository.getPlayerStat(uuid) == null) {
            PlayerStatRepository.createPlayerStat(uuid)
        }
    }

    @EventHandler
    fun onPlayerDamageEvent(event: EntityDamageByEntityEvent) {

        if (event.damager !is Player) {
            return
        }

        event.isCancelled = true
    }

}