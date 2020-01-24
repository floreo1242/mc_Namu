package com.kkomi.treeisland.plugin.monster.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.monster.model.entity.MonsterDrop
import java.io.File

class MonsterFileDataSource(
        dataFolder: File,
        classType: Class<MonsterDrop>
) : MonsterDataSource, FileDataSource<MonsterDrop>(dataFolder, classType) {

    init {
        if (getValueList().isEmpty()) {
            createMonster(MonsterDrop("ZOMBIE", 10, 10))
        }
    }

    override fun getMonster(name: String): MonsterDrop? {
        return getValue(name)
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

    override fun reloadMonsters() {
        loadFiles()
    }
}