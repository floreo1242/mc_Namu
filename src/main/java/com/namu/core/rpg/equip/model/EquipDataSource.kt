package com.namu.core.rpg.equip.model

import com.namu.core.rpg.equip.model.entity.Equip
import com.namu.core.rpg.guild.model.entity.Guild

interface EquipDataSource {

    fun getEquip(uuid: String) : Equip?

    fun editEquip(equip : Equip)

    fun saveEquip(equip : Equip)

}