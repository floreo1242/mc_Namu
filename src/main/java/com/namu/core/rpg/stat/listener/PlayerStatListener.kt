package com.namu.core.rpg.stat.listener

import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.level.api.PlayerLevelUpEvent
import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.StatConfigRepository
import com.namu.core.rpg.stat.model.entity.PlayerStat
import com.namu.core.rpg.stat.util.edit
import com.namu.core.rpg.stat.util.playerStat
import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerStatListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerStatRepository.getPlayerStat(uuid) == null) {
            PlayerStatRepository.createPlayerStat(
                    PlayerStat(
                            uuid,
                            mutableMapOf(
                                    StatType.STRENGTH to 0,
                                    StatType.HEALTH to 0,
                                    StatType.MANA to 0,
                                    StatType.WALK_SPEED to 0,
                                    StatType.CRITICAL_CHANCE to 0,
                                    StatType.HAND_DEXTERITY to 0
                            ),
                            0
                    )
            )
        }
    }

    @EventHandler
    fun onPlayerLevelUpEvent(event: PlayerLevelUpEvent) {
        val value = StatConfigRepository.getStatConfig().levelUpStat
        event.player.playerStat.apply {
            leftPoint += value
        }.edit()
        event.player.sendInfoMessage("[${"&6$value"}&f] 스텟포인트를 획득 하였습니다.")
    }

}