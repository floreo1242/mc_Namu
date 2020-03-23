package com.namu.core.rpg.skill.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.namu.core.rpg.skill.inventory.SkillOptionInventory
import com.namu.core.rpg.skill.model.PlayerSkillInfoRepository
import com.namu.core.rpg.skill.util.playerSkill
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class SkillOptionInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != SkillOptionInventory.TITLE) {
            return
        }

        event.isCancelled = true

        when (event.slot) {
            2 -> {
                player.playerSkill.isSmartSlot = !player.playerSkill.isSmartSlot
                PlayerSkillInfoRepository.editPlayerSkillInfo(player.playerSkill)
                inventory.setItem(
                        2,
                        if (player.playerSkill.isSmartSlot) {
                            SkillOptionInventory.SMART_KEY_USE
                        } else {
                            SkillOptionInventory.SMART_KEY_UN_USE
                        }
                )
                player.updateInventory()
            }
            else -> return
        }
    }

}