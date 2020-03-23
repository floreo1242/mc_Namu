package com.namu.core.rpg.quest.util

import com.namu.core.rpg.quest.model.PlayerQuestRepository
import com.namu.core.rpg.quest.model.entity.PlayerQuest
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

val Player.playerQuest: PlayerQuest
    get() {
        val playerQuest = PlayerQuestRepository.getPlayerQuest(uniqueId.toString())
        if (playerQuest == null) {
            val createdPlayerQuest = PlayerQuest(uniqueId.toString(), mutableListOf(), mutableMapOf())
            PlayerQuestRepository.createPlayerQuest(createdPlayerQuest)
            return createdPlayerQuest
        }
        return playerQuest
    }

val OfflinePlayer.playerQuest: PlayerQuest?
    get() {
        return PlayerQuestRepository.getPlayerQuest(uniqueId.toString())
    }

fun PlayerQuest.edit() {
    PlayerQuestRepository.editPlayerQuest(this)
    PlayerQuestRepository.savePlayerQuest(this)
}