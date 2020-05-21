package com.namu.core.rpg.equip.inventory

import com.kkomi.devlibrary.inventory.InventoryManager
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import com.namu.core.utility.itemdb.model.EquipmentType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class EquipInventory(player: Player) : InventoryManager(player) {

    companion object {
        const val TITLE = "장비창"
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, TITLE)

    override fun setBasicFrame() {
        val playerEquipInfo = PlayerEquipInfoRepository.getPlayerEquipInfo(player.uniqueId.toString())!!

        EquipmentType.values().forEach { inventory.setItem(it.index, playerEquipInfo.items[it]) }
    }

}