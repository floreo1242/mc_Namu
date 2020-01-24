package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerSkillInfoListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val playerSkillInfo = PlayerSkillInfoRepository.getPlayerSkillInfo(event.player.uniqueId.toString())

        if (playerSkillInfo == null) {
            PlayerSkillInfoRepository.addPlayerSkillInfo(PlayerSkillInfo(event.player.uniqueId.toString(), false, mutableListOf()))
        }
    }

    @EventHandler
    fun onPlayerChangeCursor(event: PlayerItemHeldEvent) {
        println(event.newSlot)
    }
}