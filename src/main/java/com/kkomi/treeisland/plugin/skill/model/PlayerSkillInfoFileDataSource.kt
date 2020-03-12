package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import java.io.File

class PlayerSkillInfoFileDataSource(
        dataFolder: File,
        classType: Class<PlayerSkillInfo>
) : PlayerSkillInfoDataSource, FileDataSource<PlayerSkillInfo>(dataFolder, classType) {

    override fun getPlayerSkillInfo(name: String): PlayerSkillInfo? {
        return getValue(name)
    }

    override fun getPlayerSkillInfoList(): List<PlayerSkillInfo> {
        return getValueList()
    }

    override fun addPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        setValue(playerSkillInfo.uuid, playerSkillInfo)
    }

    override fun editPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        setValue(playerSkillInfo.uuid, playerSkillInfo)
    }

    override fun savePlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        saveFile(playerSkillInfo.uuid, playerSkillInfo)
    }

    override fun removePlayerSkillInfo(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadPlayerSkillInfo() {
        loadFiles()
    }

}