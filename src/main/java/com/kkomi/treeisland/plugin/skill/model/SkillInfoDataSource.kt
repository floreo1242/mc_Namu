package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo

interface SkillInfoDataSource {

    fun getSkillInfo(name: String): SkillInfo?

    fun getSkillInfoList(): List<SkillInfo>

    fun addSkillInfo(skillInfo: SkillInfo)

    fun editSkillInfo(skillInfo: SkillInfo)

    fun removeSkillInfo(name: String)

    fun reloadSkillInfo()

}