package com.namu.core.rpg.mana.listener

import com.namu.core.rpg.mana.model.PlayerManaRepository
import com.namu.core.rpg.mana.model.entity.PlayerMana
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerManaListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerManaRepository.getPlayerMana(uuid) == null) {
            PlayerMana(100, 100, uuid).run {
                PlayerManaRepository.createPlayerMana(this)
                PlayerManaRepository.savePlayerMana(this)
            }
        }
    }

}