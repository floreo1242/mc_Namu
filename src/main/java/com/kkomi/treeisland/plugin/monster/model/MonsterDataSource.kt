package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.plugin.monster.model.entity.MonsterDrop

interface MonsterDataSource {

    fun getMonster(name: String): MonsterDrop?

    fun createMonster(monster: MonsterDrop)

    fun editMonster(monster: MonsterDrop)

    fun removeMonster(name: String)

    fun reloadMonsters()

}