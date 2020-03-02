package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
import com.kkomi.treeisland.plugin.skill.inventory.SkillOptionInventory
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class SkillOptionInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != SkillOptionInventory.TITLE) {
            return
        }

        event.isCancelled = true

        when (event.slot) {
            2 -> {
                playerInfo.skillInfo.isSmartSlot = !playerInfo.skillInfo.isSmartSlot
                PlayerSkillInfoRepository.editPlayerSkillInfo(playerInfo.skillInfo)
                inventory.setItem(
                        2,
                        if (playerInfo.skillInfo.isSmartSlot) {
                            SkillOptionInventory.SMART_KEY_USE
                        } else {
                            SkillOptionInventory.SMART_KEY_UN_USE
                        }
                )
                playerInfo.player.updateInventory()
            }
            else -> return
        }
    }

}