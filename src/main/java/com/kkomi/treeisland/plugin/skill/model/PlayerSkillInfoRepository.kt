package com.kkomi.treeisland.plugin.skill.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import java.io.File

object PlayerSkillInfoRepository {

    private val playerSkillInfoDataSource = PlayerSkillInfoFileDataSource(File(Treeisland.skillPlugin.dataFolder.path + "/players"), PlayerSkillInfo::class.java)

    fun getPlayerSkillInfo(name: String): PlayerSkillInfo? {
        return playerSkillInfoDataSource.getPlayerSkillInfo(name)
    }

    fun getPlayerSkillInfoList(): List<PlayerSkillInfo> {
        return playerSkillInfoDataSource.getPlayerSkillInfoList()
    }

    fun addPlayerSkillInfo(playerSkillInfo: PlayerSkillInfo) {
        playerSkillInfoDataSource.addPlayerSkillInfo(playerSkillInfo)
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