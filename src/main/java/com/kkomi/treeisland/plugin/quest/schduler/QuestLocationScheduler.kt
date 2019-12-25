package com.kkomi.treeisland.plugin.quest.schduler

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
                .forEach {
                    it.second.checkQuestAmount(QuestAction.MOVE_LOCATION) { quest ->
                        it.first.location.distance(quest.locationObject) <= 1
                    }
                }
    }

    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }

}