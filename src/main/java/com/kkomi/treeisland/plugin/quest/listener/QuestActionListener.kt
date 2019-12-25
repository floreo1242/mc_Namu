package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.PlayerDropItemEvent

class QuestActionListener : Listener {

    @EventHandler
    fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
        event.player.updateInventory()
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler
    fun onPlayerPickupItemEvent(event: EntityPickupItemEvent) {
        (event.entity as Player).updateInventory()
        (event.entity as Player).getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        (event.whoClicked as Player).getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.BLOCK_BREAK) { quest -> quest.stringObject == event.block.type.toString() }
    }

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.BLOCK_PLACE) { quest -> quest.stringObject == event.block.type.toString() }
    }

    @EventHandler
    fun onEntityDeathEvent(event: EntityDeathEvent) {
        if (event.entity.killer == null) {
            return
        }
        event.entity.killer.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.KILL_ENTITY) { quest -> quest.stringObject == event.entity.name }
    }

}