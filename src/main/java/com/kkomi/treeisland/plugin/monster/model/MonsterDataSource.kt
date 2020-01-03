package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.plugin.monster.model.entity.Monster

interface MonsterDataSource {

    fun getMonster(name: String): Monster?

    fun createMonster(monster: Monster)

    fun editMonster(monster: Monster)

    fun removeMonster(name: String)

    fun reloadMonsters()

}