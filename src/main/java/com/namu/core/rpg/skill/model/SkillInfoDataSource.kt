package com.namu.core.rpg.skill.model

import com.namu.core.rpg.skill.model.entity.SkillInfo

interface SkillInfoDataSource {

    fun getSkillInfo(name: String): SkillInfo?

    fun getSkillInfoList(): List<SkillInfo>

    fun createSkillInfo(skillInfo: SkillInfo)

    fun editSkillInfo(skillInfo: SkillInfo)

    fun removeSkillInfo(name: String)

    fun reloadSkillInfo()

    fun saveSkillInfo(skillInfo: SkillInfo)

}