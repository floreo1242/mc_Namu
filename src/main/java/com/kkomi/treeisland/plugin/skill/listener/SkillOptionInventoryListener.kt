package com.kkomi.treeisland.plugin.skill.listener

import com.kkomi.devlibrary.extension.getDisplay
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.inventory.QuestCancelInventory
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.QuestRepository
import com.kkomi.treeisland.plugin.skill.inventory.SkillOptionInventory
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.SkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.kkomi.treeisland.plugin.skill.model.entity.SkillInfo
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot

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