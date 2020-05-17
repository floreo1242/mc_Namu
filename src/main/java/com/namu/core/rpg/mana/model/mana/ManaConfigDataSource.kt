package com.namu.core.rpg.mana.model.mana

import com.namu.core.rpg.mana.model.mana.entity.ManaConfig

interface ManaConfigDataSource {

    fun getManaConfig(): ManaConfig

    fun setManaConfig(manaConfig : ManaConfig)

    fun reloadManaConfig()

}