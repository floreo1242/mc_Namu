package com.kkomi.mana.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.mana.model.PlayerManaDataSource
import com.namu.core.rpg.mana.model.entity.PlayerMana
import java.io.File

class PlayerManaFileDataSource(
        dataFolder: File,
        classType: Class<PlayerMana>
) : PlayerManaDataSource, FileDataSource<PlayerMana>(dataFolder, classType) {

    override fun getPlayerMana(uuid: String): PlayerMana? {
        return getValue(uuid)
    }

    override fun getPlayerManaList(): List<PlayerMana> {
        return getValueList()
    }

    override fun createPlayerMana(playerMana: PlayerMana) {
        setValue(playerMana.uuid, playerMana)
    }

    override fun editPlayerMana(playerMana: PlayerMana) {
        setValue(playerMana.uuid, playerMana)
    }

    override fun removePlayerMana(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun savePlayerMana(playerMana: PlayerMana) {
        saveFile(playerMana.uuid, playerMana)
    }

    override fun reloadPlayerMana() {
        loadFiles()
    }

}