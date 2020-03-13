package com.kkomi.treeisland.plugin.money.model

import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney

interface MoneyDataSource {

    fun getPlayerMoney(uuid: String): PlayerMoney?

    fun getPlayerMoneyList(): List<PlayerMoney>

    fun createPlayerMoney(playerMoney: PlayerMoney)

    fun editPlayerMoney(playerMoney: PlayerMoney)

    fun removePlayerMoney(uuid: String)

    fun reloadPlayerMoney()

    fun savePlayerMoney(playerMoney: PlayerMoney)
}