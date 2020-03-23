package com.namu.core.rpg.skill.util

import com.namu.core.rpg.skill.model.PlayerSkillInfoRepository
import com.namu.core.rpg.skill.model.entity.PlayerSkillInfo
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerSkill: PlayerSkillInfo
    get() {
        val playerSkill = PlayerSkillInfoRepository.getPlayerSkillInfo(uniqueId.toString())
        if (playerSkill == null) {
            val createdPlayerSkillInfo = PlayerSkillInfo(uniqueId.toString(), true, mutableListOf())
            PlayerSkillInfoRepository.createPlayerSkillInfo(createdPlayerSkillInfo)
            return createdPlayerSkillInfo
        }
        return playerSkill
    }

val OfflinePlayer.playerSkill: PlayerSkillInfo?
    get() {
        return PlayerSkillInfoRepository.getPlayerSkillInfo(uniqueId.toString())
    }

fun PlayerSkillInfo.edit() {
    PlayerSkillInfoRepository.editPlayerSkillInfo(this)
    PlayerSkillInfoRepository.savePlayerSkillInfo(this)
}