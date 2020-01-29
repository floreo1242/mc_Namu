package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.level.api.PlayerLevelUpEvent
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import kotlin.random.Random
import kotlin.random.nextInt

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
        if (event.damager is Player) {
            event.isCancelled = true
        }

        if (event.entity is Player) {
            if (PlayerStatRepository.getPlayerStat(event.entity.uniqueId.toString()) == null) {
                return
            }

            val player = event.entity as Player
            val stat = player.getPlayerInfo().statInfo

            if (((stat.finalStat[StatOption.AGILITY]
                            ?: 0) * StatConfigRepository.getStatConfig().agiPointByValue) >= Random.nextInt(1..100)) {
                event.isCancelled = true
            } else {
                event.damage -= ((stat.finalStat[StatOption.DEFENSE] ?: 0) * StatConfigRepository.getStatConfig().vitPointByValue)
            }
        }
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