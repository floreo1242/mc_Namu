package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo

interface PlayerSkillInfoDataSource {

    fun getPlayerSkillInfo(name: String): PlayerSkillInfo?

    fun getPlayerSkillInfoList(): List<PlayerSkillInfo>

    fun addPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo)

    fun editPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo)

    fun removePlayerSkillInfo(name: String)

    fun reloadPlayerSkillInfo()

}