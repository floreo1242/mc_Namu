package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.library.extension.replaceChatColorCode
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import java.io.File

object SkillInfoRepository {

    private val skillInfoDataSource = SkillInfoFileDataSource(File(Treeisland.skillPlugin.dataFolder.path + "/skills"), SkillInfo::class.java)

    fun getSkillInfo(name: String): SkillInfo? {
        return skillInfoDataSource.getSkillInfo(name)
    }

    fun getSkillInfoByDisplay(display: String): SkillInfo? {
        return getSkillInfoList()
                .find {
                    it.displayName.replaceChatColorCode() == display.replaceChatColorCode()
                }
    }

    fun getSkillInfoList(): List<SkillInfo> {
        return skillInfoDataSource.getSkillInfoList()
    }

    fun addSkillInfo(skillInfo: SkillInfo) {
        skillInfoDataSource.addSkillInfo(skillInfo)
    }

    fun editSkillInfo(skillInfo: SkillInfo) {
        skillInfoDataSource.editSkillInfo(skillInfo)
    }

    fun removeSkillInfo(name: String) {
        skillInfoDataSource.removeSkillInfo(name)
    }

    fun reloadSkillInfo() {
        skillInfoDataSource.reloadSkillInfo()
    }

}