package com.namu.core.rpg.mana.model

import com.namu.core.rpg.mana.model.entity.PlayerMana

interface PlayerManaDataSource {

    fun getPlayerMana(name: String): PlayerMana?

    fun getPlayerManaList(): List<PlayerMana>

    fun createPlayerMana(playerMana: PlayerMana)

    fun editPlayerMana(playerMana: PlayerMana)

    fun removePlayerMana(name: String)

    fun savePlayerMana(playerMana: PlayerMana)

    fun reloadPlayerMana()

}