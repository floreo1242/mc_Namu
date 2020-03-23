package com.namu.core.economy.money.util

import com.namu.core.economy.money.model.PlayerMoneyRepository
import com.namu.core.economy.money.model.entity.PlayerMoney
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerMoney: PlayerMoney
    get() {
        val playerMoney = PlayerMoneyRepository.getPlayerMoney(uniqueId.toString())
        if (playerMoney == null) {
            val createdPlayerMoney = PlayerMoney(uniqueId.toString(), 0)
            PlayerMoneyRepository.createPlayerMoney(createdPlayerMoney)
            return createdPlayerMoney
        }
        return playerMoney
    }

val OfflinePlayer.playerMoney: PlayerMoney?
    get() {
        return PlayerMoneyRepository.getPlayerMoney(uniqueId.toString())
    }

fun PlayerMoney.edit() {
    PlayerMoneyRepository.editPlayerMoney(this)
    PlayerMoneyRepository.savePlayerMoney(this)
}