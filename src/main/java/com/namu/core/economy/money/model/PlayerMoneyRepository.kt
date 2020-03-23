package com.namu.core.economy.money.model

import com.namu.core.MainCore
import com.namu.core.economy.money.model.entity.PlayerMoney

object PlayerMoneyRepository {

    private val playerMoneyDataSource = MoneyFileDataSource(MainCore.moneyPlugin.dataFolder, PlayerMoney::class.java)

    fun getPlayerMoney(uuid: String): PlayerMoney? {
        return playerMoneyDataSource.getPlayerMoney(uuid)
    }

    fun getPlayerMoneyList(): List<PlayerMoney> {
        return playerMoneyDataSource.getPlayerMoneyList()
    }

    fun createPlayerMoney(playerMoney: PlayerMoney) {
        return playerMoneyDataSource.createPlayerMoney(playerMoney)
    }

    fun editPlayerMoney(playerMoney: PlayerMoney) {
        playerMoneyDataSource.editPlayerMoney(playerMoney)
    }

    fun removePlayerMoney(uuid: String) {
        playerMoneyDataSource.removePlayerMoney(uuid)
    }

    fun savePlayerMoney(playerMoney: PlayerMoney) {
        playerMoneyDataSource.savePlayerMoney(playerMoney)
    }

    fun reloadPlayerMoney() {
        playerMoneyDataSource.reloadPlayerMoney()
    }

}