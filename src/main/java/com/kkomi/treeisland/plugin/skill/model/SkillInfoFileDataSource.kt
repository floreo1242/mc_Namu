package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import java.io.File

class SkillInfoFileDataSource(
        dataFolder: File,
        classType: Class<SkillInfo>
) : SkillInfoDataSource, FileDataSource<SkillInfo>(dataFolder, classType) {

    override fun getSkillInfo(name: String): SkillInfo? {
        return getValue(name)
    }

    override fun getSkillInfoList(): List<SkillInfo> {
        return getValueList()
    }

    override fun addSkillInfo(skillInfo: SkillInfo) {
        setValue(skillInfo.name, skillInfo)
    }

    override fun editSkillInfo(skillInfo: SkillInfo) {
        setValue(skillInfo.name, skillInfo)
    }

    override fun saveSkillInfo(skillInfo: SkillInfo) {
        saveFile(skillInfo.name, skillInfo)
    }

    override fun removeSkillInfo(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadSkillInfo() {
        loadFiles()
    }

}