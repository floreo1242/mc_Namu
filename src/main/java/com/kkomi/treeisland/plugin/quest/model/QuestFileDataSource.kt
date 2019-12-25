package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.quest.model.entity.Quest
import java.io.File

class QuestFileDataSource(
        dataFolder: File,
        classType: Class<Quest>
) : QuestDataSource, FileDataSource<Quest>(dataFolder, classType) {

    override fun getQuest(name: String): Quest? {
        return getValue(name)
    }

    override fun getQuestList(): List<Quest> {
        return getValueList()
    }

    override fun addQuest(quest: Quest) {
        setValue(quest.name, quest)
        saveFile(quest.name, quest)
    }

    override fun editQuest(quest: Quest) {
        setValue(quest.name, quest)
        saveFile(quest.name, quest)
    }

    override fun removeQuest(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadQuests() {
        loadFiles()
    }

}