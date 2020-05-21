package com.namu.core.rpg.calculate.statusbar

import com.kkomi.devlibrary.schedular.TimerManager
import org.bukkit.Bukkit

class StatusBarScheduler : TimerManager() {
    override fun endEventTimer() {
    }

    override fun finalEventEndTimer() {
    }

    override fun runningEventTimer(count: Int) {
        Bukkit.getOnlinePlayers().forEach { BasicStatusBar.show(it) }
    }

    override fun startEventTimer() {
    }
}