package com.kkomi.treeisland.plugin.role.listener

import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.RoleRepository
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import com.nisovin.magicspells.MagicSpells
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerRoleListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()
        val playerRole = PlayerRoleRepository.getPlayerRole(uuid)
        if (playerRole == null) {
            PlayerRoleRepository.addPlayerRole(PlayerRole(uuid, RoleRepository.getRole("모험가")!!))
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player
        val mainItemStack = player.inventory.itemInMainHand

        if (mainItemStack == null ||
                mainItemStack.type == Material.AIR ||
                mainItemStack.type == Material.IRON_BARDING ||
                player.inventory.heldItemSlot != 0 ||
                event.hand == EquipmentSlot.OFF_HAND
        ) {
            return
        }

        MagicSpells.getSpellByInGameName(player.getPlayerInfo().roleInfo.role.defaultAttackSpellName).cast(player)
    }
}