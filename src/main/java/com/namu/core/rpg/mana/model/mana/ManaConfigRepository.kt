package com.namu.core.rpg.mana.model.mana

import com.namu.core.MainCore
import com.namu.core.rpg.mana.model.mana.entity.ManaConfig
import java.io.File

object ManaConfigRepository {

    private val manaConfigDataSource = ManaConfigFileDataSource(File(MainCore.manaPlugin.dataFolder.path))

    fun getManaConfig(): ManaConfig {
        return manaConfigDataSource.getManaConfig()
    }

    fun setManaConfig(manaConfig : ManaConfig) {
        manaConfigDataSource.setManaConfig(manaConfig)
    }

    fun reloadManaConfig() {
        manaConfigDataSource.reloadManaConfig()
    }

}