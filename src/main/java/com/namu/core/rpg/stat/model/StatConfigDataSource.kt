package com.namu.core.rpg.stat.model

import com.namu.core.rpg.stat.model.entity.StatConfig

interface StatConfigDataSource {

    fun getStatConfig(): StatConfig

    fun setStatConfig(statConfig : StatConfig)

    fun reloadStatConfig()

}