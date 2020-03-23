package com.namu.core.rpg.stat.model

import com.namu.core.MainCore
import com.namu.core.rpg.stat.model.entity.StatConfig

object StatConfigRepository {

    private val statConfigDataSource = StatConfigFileDataSource(MainCore.statPlugin.dataFolder)

    fun getStatConfig(): StatConfig {
        return statConfigDataSource.getStatConfig()
    }

    fun editStatConfig(statConfig : StatConfig) {
        statConfigDataSource.setStatConfig(statConfig)
    }

    fun reloadStatConfig() {
        statConfigDataSource.reloadStatConfig()
    }

}