package com.namu.core.rpg.monster.model

import com.namu.core.rpg.monster.model.entity.MonsterDrop

interface MonsterDataSource {

    fun getMonster(name: String): MonsterDrop?

    fun createMonster(monster: MonsterDrop)

    fun editMonster(monster: MonsterDrop)

    fun removeMonster(name: String)

    fun reloadMonsters()

    fun saveMonster(monster: MonsterDrop)

    fun getMonsterList(): List<MonsterDrop>

}