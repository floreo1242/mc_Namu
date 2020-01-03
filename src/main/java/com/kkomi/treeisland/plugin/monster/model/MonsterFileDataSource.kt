package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.monster.model.entity.Monster
import java.io.File

class MonsterFileDataSource(
        dataFolder: File,
        classType: Class<Monster>
) : MonsterDataSource, FileDataSource<Monster>(dataFolder, classType) {

    override fun getMonster(name: String): Monster? {
        return getValue(name)
    }

    override fun createMonster(monster: Monster) {
        setValue(monster.name, monster)
        saveFile(monster.name, monster)
    }

    override fun editMonster(monster: Monster) {
        setValue(monster.name, monster)
        saveFile(monster.name, monster)
    }

    override fun removeMonster(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadMonsters() {
        loadFiles()
    }
}