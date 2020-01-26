package com.kkomi.treeisland.plugin.quest.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.quest.model.entity.QuestAction
import com.kkomi.treeisland.plugin.skill.api.EntityDeathBySpellCasterEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class QuestActionListener : Listener {

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerInteractEntityEvent(event: PlayerInteractEntityEvent) {
        event.player.updateInventory()
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.NPC_TALK) { questObjective -> event.rightClicked.name.toUpperCase() == questObjective.target.toUpperCase() }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
        event.player.updateInventory()
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerPickupItemEvent(event: EntityPickupItemEvent) {
        (event.entity as Player).updateInventory()
        (event.entity as Player).getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        (event.player as Player).updateInventory()
        (event.player as Player).getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        (event.whoClicked as Player).getPlayerInfo().questInfo.checkQuestAmount(QuestAction.FARMING_ITEM) { true }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.BLOCK_BREAK) { questObjective -> event.block.type.toString().toUpperCase() == questObjective.target.toUpperCase() }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        event.player.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.BLOCK_PLACE) { questObjective -> event.block.type.toString().toUpperCase() == questObjective.target.toUpperCase() }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onEntityDeathEvent(event: EntityDeathBySpellCasterEvent) {
        event.caster.getPlayerInfo().questInfo.checkQuestAmount(QuestAction.KILL_ENTITY) { questObjective -> event.entity.name.toUpperCase() == questObjective.target.toUpperCase() }
    }

}