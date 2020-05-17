package com.namu.core.rpg.stat.util

import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.entity.PlayerStat
import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerStat: PlayerStat
    get() {
        val playerStat = PlayerStatRepository.getPlayerStat(uniqueId.toString())
        if (playerStat == null) {
            val createdPlayerStat = PlayerStat(
                    uniqueId.toString(),
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
            PlayerStatRepository.createPlayerStat(createdPlayerStat)
            return createdPlayerStat
        }
        return playerStat
    }

val OfflinePlayer.playerStat: PlayerStat?
    get() {
        return PlayerStatRepository.getPlayerStat(uniqueId.toString())
    }

fun PlayerStat.edit() {
    PlayerStatRepository.editPlayerStat(this)
    PlayerStatRepository.savePlayerStat(this)
}