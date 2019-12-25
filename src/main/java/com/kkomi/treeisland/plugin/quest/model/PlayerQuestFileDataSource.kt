package com.kkomi.treeisland.plugin.quest.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import java.io.File

class PlayerQuestFileDataSource(
        dataFolder: File,
        classType: Class<PlayerQuest>
) : PlayerQuestDataSource, FileDataSource<PlayerQuest>(dataFolder, classType) {

    override fun getPlayerQuest(uuid: String): PlayerQuest? {
        return getValue(uuid)
    }

    override fun getPlayerQuestList(): List<PlayerQuest> {
        return getValueList()
    }

    override fun addPlayerQuest(playerQuest: PlayerQuest) {
        setValue(playerQuest.uuid, playerQuest)
        saveFile(playerQuest.uuid, playerQuest)
    }

    override fun editPlayerQuest(playerQuest: PlayerQuest) {
        setValue(playerQuest.uuid, playerQuest)
        saveFile(playerQuest.uuid, playerQuest)
    }

    override fun removePlayerQuest(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

}