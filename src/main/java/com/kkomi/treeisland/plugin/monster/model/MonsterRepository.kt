package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.monster.model.entity.MonsterDrop

object MonsterRepository {

    private val monsterDataSource = MonsterFileDataSource(Treeisland.monsterPlugin.dataFolder, MonsterDrop::class.java)

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

    fun reloadMonsters() {
        monsterDataSource.reloadMonsters()
    }
}