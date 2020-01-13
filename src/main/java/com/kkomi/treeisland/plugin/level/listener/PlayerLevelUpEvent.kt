package com.kkomi.treeisland.plugin.level.listener

import com.kkomi.treeisland.plugin.integration.PlayerInfo
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

data class PlayerLevelUpEvent(
        val isAsync: Boolean,
        val playerInfo: PlayerInfo
) : Event(isAsync) {

    private val handlers = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlers
    }

    companion object {

        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlerList() : HandlerList {
            return handlers
        }
    }

}