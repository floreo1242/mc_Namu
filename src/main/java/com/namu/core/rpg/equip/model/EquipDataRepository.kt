package com.namu.core.rpg.equip.model

import com.namu.core.MainCore
import com.namu.core.rpg.equip.model.entity.Equip
import com.namu.core.rpg.guild.model.GuildFileDataSource
import com.namu.core.rpg.guild.model.PlayerGuildFileDataSource
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.PlayerGuild
import java.io.File

object EquipDataRepository {

    private val equipDataSource = EquipFileDataSource(File(MainCore.guildPlugin.dataFolder.path + "/guilds"), Equip::class.java)

    fun getEquip(uuid: String) : Equip?{
        return equipDataSource.getEquip(uuid)
    }

    fun editEquip(equip: Equip){
        equipDataSource.editEquip(equip)
    }

    fun saveEquip(equip: Equip) {
        equipDataSource.saveEquip(equip)
    }
}