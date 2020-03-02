package com.kkomi.treeisland.plugin.integration.scheduler

import com.kkomi.devlibrary.schedular.TimerManager
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import org.bukkit.Bukkit

class PlayerDataScheduler : TimerManager() {
    override fun startEventTimer() {
    }

    override fun runningEventTimer(count: Int) {
        Bukkit.getOnlinePlayers()
                .forEach {
                    it.getPlayerInfo().showActionBar()
                }
    }

    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }
}