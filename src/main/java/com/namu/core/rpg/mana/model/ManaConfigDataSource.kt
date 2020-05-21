package com.namu.core.rpg.mana.model

import com.namu.core.rpg.mana.model.entity.ManaConfig

interface ManaConfigDataSource {

    fun getManaConfig(): ManaConfig

    fun setManaConfig(manaConfig : ManaConfig)

    fun reloadManaConfig()

}