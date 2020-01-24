package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig

interface StatConfigDataSource {

    fun getStatConfig(): StatConfig

    fun editStatConfig(statConfig : StatConfig)

    fun reloadStatConfig()

}