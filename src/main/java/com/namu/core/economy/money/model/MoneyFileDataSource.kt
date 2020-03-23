package com.namu.core.economy.money.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.economy.money.model.MoneyDataSource
import com.namu.core.economy.money.model.entity.PlayerMoney
import java.io.File

class MoneyFileDataSource(
        dataFolder: File,
        classType: Class<PlayerMoney>
) : MoneyDataSource, FileDataSource<PlayerMoney>(dataFolder, classType) {

    override fun getPlayerMoney(uuid: String): PlayerMoney? {
        return getValue(uuid)
    }

    override fun getPlayerMoneyList(): List<PlayerMoney> {
        return getValueList()
    }

    override fun createPlayerMoney(playerMoney: PlayerMoney) {
        setValue(playerMoney.uuid, playerMoney)
        saveFile(playerMoney.uuid, playerMoney)
    }

    override fun editPlayerMoney(playerMoney: PlayerMoney) {
        setValue(playerMoney.uuid, playerMoney)
        saveFile(playerMoney.uuid, playerMoney)
    }

    override fun savePlayerMoney(playerMoney: PlayerMoney) {
        saveFile(playerMoney.uuid, playerMoney)
    }

    override fun removePlayerMoney(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun reloadPlayerMoney() {
        loadFiles()
    }

}