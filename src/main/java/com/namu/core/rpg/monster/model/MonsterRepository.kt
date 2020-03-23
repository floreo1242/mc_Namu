package com.namu.core.rpg.monster.model

import com.namu.core.MainCore
import com.namu.core.rpg.monster.model.entity.MonsterDrop

object MonsterRepository {

    private val monsterDataSource = MonsterFileDataSource(MainCore.monsterPlugin.dataFolder, MonsterDrop::class.java)

    fun getMonster(name: String): MonsterDrop? {
        return monsterDataSource.getMonster(name)
    }

    fun createMonster(monster: MonsterDrop) {
        monsterDataSource.createMonster(monster)
    }

    fun editMonster(monster: MonsterDrop) {
        monsterDataSource.editMonster(monster)
    }

    fun removeMonster(name: String) {
        monsterDataSource.removeMonster(name)
    }

    fun saveMonster(monster: MonsterDrop) {
        monsterDataSource.saveMonster(monster)
    }

    fun reloadMonster() {
        monsterDataSource.reloadMonsters()
    }

    fun getMonsterList() : List<MonsterDrop> {
        return monsterDataSource.getMonsterList()
    }
}