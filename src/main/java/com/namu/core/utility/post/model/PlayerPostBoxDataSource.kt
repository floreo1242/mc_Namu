package com.namu.core.utility.post.model

import com.namu.core.utility.post.model.entity.PlayerPostBox

interface PlayerPostBoxDataSource {

    fun getPlayerPostBox(name: String): PlayerPostBox?

    fun getPlayerPostBoxList(): List<PlayerPostBox>

    fun createPlayerPostBox(playerPostBox: PlayerPostBox)

    fun editPlayerPostBox(playerPostBox: PlayerPostBox)

    fun removePlayerPostBox(name: String)

    fun savePlayerPostBox(playerPostBox: PlayerPostBox)

    fun reloadPlayerPostBox()

}