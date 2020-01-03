package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.monster.model.entity.Monster

object MonsterRepository {

    private val monsterDataSource = MonsterFileDataSource(Treeisland.monsterPlugin.dataFolder, Monster::class.java)

    fun getMonster(name: String): Monster? {
        return monsterDataSource.getMonster(name)
    }

    fun createMonster(monster: Monster) {
        monsterDataSource.createMonster(monster)
    }

    fun editMonster(monster: Monster) {
        monsterDataSource.editMonster(monster)
    }

    fun removeMonster(name: String) {
        monsterDataSource.removeMonster(name)
    }

    fun reloadMonsters() {
        monsterDataSource.reloadMonsters()
    }
}