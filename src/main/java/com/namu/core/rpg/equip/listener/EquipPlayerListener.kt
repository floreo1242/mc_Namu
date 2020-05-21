package com.namu.core.rpg.equip.listener

import com.namu.core.utility.itemdb.model.EquipmentType
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import com.namu.core.rpg.equip.model.entity.PlayerEquipInfo
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class EquipPlayerListener : Listener {

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val playerEquipInfo = PlayerEquipInfoRepository.getPlayerEquipInfo(event.player.uniqueId.toString())
        if (playerEquipInfo == null) {
            PlayerEquipInfoRepository.createPlayerEquipInfo(
                    PlayerEquipInfo(
                            EquipmentType.values().map { it to ItemStack(Material.AIR) }.toMap(),
                            event.player.uniqueId.toString()
                    )
            )
        }
    }

}