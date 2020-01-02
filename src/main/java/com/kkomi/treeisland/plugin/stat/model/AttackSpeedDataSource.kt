package com.kkomi.treeisland.plugin.stat.model

interface AttackSpeedDataSource {

    fun getAttackSpeed(subType : String) : Double

}