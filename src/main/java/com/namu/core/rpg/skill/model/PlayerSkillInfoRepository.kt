package com.namu.core.rpg.skill.model

import com.namu.core.MainCore
import com.namu.core.rpg.skill.model.entity.PlayerSkillInfo
import java.io.File

object PlayerSkillInfoRepository {

    private val playerSkillInfoDataSource = PlayerSkillInfoFileDataSource(File(MainCore.skillPlugin.dataFolder.path + "/players"), PlayerSkillInfo::class.java)

    fun getPlayerSkillInfo(name: String): PlayerSkillInfo? {
        return playerSkillInfoDataSource.getPlayerSkillInfo(name)
    }

    fun getPlayerSkillInfoList(): List<PlayerSkillInfo> {
        return playerSkillInfoDataSource.getPlayerSkillInfoList()
    }

    fun createPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        playerSkillInfoDataSource.createPlayerSkillInfo(playerSkillInfo)
    }

    fun editPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        playerSkillInfoDataSource.editPlayerSkillInfo(playerSkillInfo)
    }

    fun savePlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        playerSkillInfoDataSource.savePlayerSkillInfo(playerSkillInfo)
    }

    fun removePlayerSkillInfo(name: String) {
        playerSkillInfoDataSource.removePlayerSkillInfo(name)
    }

    fun reloadPlayerSkillInfo() {
        playerSkillInfoDataSource.reloadPlayerSkillInfo()
    }

}