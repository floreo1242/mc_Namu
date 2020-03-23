package com.namu.core.rpg.quest.schduler

import com.kkomi.devlibrary.extension.parseLocation
import com.kkomi.devlibrary.schedular.TimerManager
import com.namu.core.rpg.quest.model.entity.QuestAction
import com.namu.core.rpg.quest.util.playerQuest
import org.bukkit.Bukkit

class QuestLocationScheduler : TimerManager() {

    override fun startEventTimer() {

    }

    override fun runningEventTimer(count: Int) {
        Bukkit.getOnlinePlayers()
                .map { it.player!! to it.playerQuest }
                .forEach { (player, playerQuestInfo) ->
                    playerQuestInfo.checkQuestAmount(QuestAction.MOVE_LOCATION) { questObjective ->

                        if (player.location.world != questObjective.target.parseLocation().world) {
                            return@checkQuestAmount false
                        }

                        player.location.distance(questObjective.target.parseLocation()) <= 1

                    }
                }
    }

    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }

}