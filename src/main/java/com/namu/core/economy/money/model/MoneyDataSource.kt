package com.namu.core.economy.money.model

import com.namu.core.economy.money.model.entity.PlayerMoney

interface MoneyDataSource {

    fun getPlayerMoney(uuid: String): PlayerMoney?

    fun getPlayerMoneyList(): List<PlayerMoney>

    fun createPlayerMoney(playerMoney: PlayerMoney)

    fun editPlayerMoney(playerMoney: PlayerMoney)

    fun removePlayerMoney(uuid: String)

    fun reloadPlayerMoney()

    fun savePlayerMoney(playerMoney: PlayerMoney)
}