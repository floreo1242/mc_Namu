package com.namu.core.rpg.monster.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.monster.model.entity.MonsterDrop
import java.io.File

class MonsterFileDataSource(
        dataFolder: File,
        classType: Class<MonsterDrop>
) : MonsterDataSource, FileDataSource<MonsterDrop>(dataFolder, classType) {

    init {
        if (getValueList().isEmpty()) {
            createMonster(MonsterDrop("ZOMBIE",listOf() ,10, 10))
        }
    }

    override fun getMonster(name: String): MonsterDrop? {
        return getValue(name)
    }

    override fun getMonsterList(): List<MonsterDrop> {
        return getValueList()
    }

    override fun createMonster(monster: MonsterDrop) {
        setValue(monster.name, monster)
        saveFile(monster.name, monster)
    }

    override fun editMonster(monster: MonsterDrop) {
        setValue(monster.name, monster)
        saveFile(monster.name, monster)
    }

    override fun removeMonster(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun saveMonster(monster: MonsterDrop) {
        saveFile(monster.name, monster)
    }

    override fun reloadMonsters() {
        loadFiles()
    }
}