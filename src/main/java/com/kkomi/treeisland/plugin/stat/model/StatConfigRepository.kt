package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig
import java.io.File

object StatConfigRepository {

    private val statConfigDataSource = StatConfigFileDataSource(Treeisland.statPlugin.dataFolder)

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