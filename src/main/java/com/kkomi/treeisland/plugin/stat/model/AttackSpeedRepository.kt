package com.kkomi.treeisland.plugin.stat.model

import com.kkomi.treeisland.Treeisland

object AttackSpeedRepository {

    private val fileDataSource = AttackSpeedFileDataSource(Treeisland.statPlugin.dataFolder)

    fun getAttackSpeed(subType: String): Double {
        return fileDataSource.getAttackSpeed(subType)
    }

}