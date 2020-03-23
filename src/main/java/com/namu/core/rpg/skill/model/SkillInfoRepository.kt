package com.namu.core.rpg.skill.model

import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.namu.core.MainCore
import com.namu.core.rpg.skill.model.entity.SkillInfo
import java.io.File

object SkillInfoRepository {

    private val skillInfoDataSource = SkillInfoFileDataSource(File(MainCore.skillPlugin.dataFolder.path + "/skills"), SkillInfo::class.java)

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

    fun createSkillInfo(skillInfo: SkillInfo) {
        skillInfoDataSource.createSkillInfo(skillInfo)
    }

    fun editSkillInfo(skillInfo: SkillInfo) {
        skillInfoDataSource.editSkillInfo(skillInfo)
    }

    fun saveSkillInfo(skillInfo: SkillInfo) {
        skillInfoDataSource.saveSkillInfo(skillInfo)
    }

    fun removeSkillInfo(name: String) {
        skillInfoDataSource.removeSkillInfo(name)
    }

    fun reloadSkillInfo() {
        skillInfoDataSource.reloadSkillInfo()
    }

}