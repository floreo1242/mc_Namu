package com.namu.core.rpg.mana.model

import com.kkomi.mana.model.PlayerManaFileDataSource
import com.namu.core.MainCore
import com.namu.core.rpg.mana.model.entity.PlayerMana
import java.io.File

object PlayerManaRepository {

    private val playerManaDataSource = PlayerManaFileDataSource(File(MainCore.manaPlugin.dataFolder.path + "/players"), PlayerMana::class.java)

    fun getPlayerMana(name: String): PlayerMana? {
        return playerManaDataSource.getPlayerMana(name)
    }

    fun getPlayerManaList(): List<PlayerMana> {
        return playerManaDataSource.getPlayerManaList()
    }

    fun createPlayerMana(playerMana: PlayerMana) {
        playerManaDataSource.createPlayerMana(playerMana)
    }

    fun editPlayerMana(playerMana: PlayerMana) {
        playerManaDataSource.editPlayerMana(playerMana)
    }
    
    fun savePlayerMana(playerMana: PlayerMana) {
        playerManaDataSource.savePlayerMana(playerMana)
    }            

    fun removePlayerMana(name: String) {
        playerManaDataSource.removePlayerMana(name)
    }

    fun reloadPlayerMana() {
        playerManaDataSource.reloadPlayerMana()
    }

}