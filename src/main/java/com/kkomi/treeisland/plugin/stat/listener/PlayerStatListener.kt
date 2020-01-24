package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.plugin.level.api.PlayerLevelUpEvent
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerStatListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerStatRepository.getPlayerStat(uuid) == null) {
            PlayerStatRepository.createPlayerStat(uuid)
        }

        PlayerStatRepository.getPlayerStat(uuid)!!.calculateStatOption(event.player)
    }

    @EventHandler
    fun onPlayerDamageEvent(event: EntityDamageByEntityEvent) {
        if (event.damager !is Player) {
            return
        }
        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerLevelUpEvent(event: PlayerLevelUpEvent) {
        val value = StatConfigRepository.getStatConfig().levelUpStat
        event.playerInfo.apply {
            statInfo.leftPoint += value
            sendInfoMessage("[${"&6$value"}&f] 스텟포인트를 획득 하였습니다.")
        }.editPlayerInfo()
    }

}