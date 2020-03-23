package com.namu.core.rpg.quest.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.quest.model.entity.PlayerQuest
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

    override fun createPlayerQuest(playerQuest: PlayerQuest) {
        setValue(playerQuest.uuid, playerQuest)
        saveFile(playerQuest.uuid, playerQuest)
    }

    override fun editPlayerQuest(playerQuest: PlayerQuest) {
        setValue(playerQuest.uuid, playerQuest)
        saveFile(playerQuest.uuid, playerQuest)
    }

    override fun savePlayerQuest(playerQuest: PlayerQuest) {
        saveFile(playerQuest.uuid, playerQuest)
    }

    override fun removePlayerQuest(uuid: String) {
        removeValue(uuid)
        deleteFile(uuid)
    }

    override fun reloadPlayerQuest() {
        loadFiles()
    }

}