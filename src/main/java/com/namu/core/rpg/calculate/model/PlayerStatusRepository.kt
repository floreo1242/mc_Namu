package com.namu.core.rpg.calculate.model

import org.bukkit.entity.Player

object PlayerStatusRepository {

    private val playerByPlayerStatus = mutableMapOf<Player, PlayerStatus>()

    fun getPlayerStatus(player: Player): PlayerStatus {
        var playerStatus = playerByPlayerStatus[player]

        if (playerStatus == null) {
            playerStatus = PlayerStatus(player.uniqueId.toString(), 0, 0, 0, 0).apply {
                calculate()
                apply()
            }
            playerByPlayerStatus[player] = playerStatus
        }

        return playerStatus
    }

}