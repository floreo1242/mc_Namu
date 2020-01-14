package com.kkomi.treeisland.plugin.quest.schduler

import com.kkomi.treeisland.library.extension.parseLocation
import com.kkomi.treeisland.library.schedular.TimerManager
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import org.bukkit.Bukkit

class QuestLocationScheduler : TimerManager() {

    override fun startEventTimer() {

    }

    override fun runningEventTimer(count: Int) {
        Bukkit.getOnlinePlayers()
                .map { it.getPlayerInfo() }
                .map { it.player to it.questInfo }
                .forEach {(player, playerQuestInfo) ->
                    playerQuestInfo.checkQuestAmount(QuestAction.MOVE_LOCATION) { questObjective ->
                        player.location.distance(questObjective.target.parseLocation()) <= 1
                    }
                }
    }

    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }

}