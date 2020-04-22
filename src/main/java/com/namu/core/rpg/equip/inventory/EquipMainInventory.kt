package com.namu.core.rpg.equip.inventory

import com.namu.core.rpg.equip.model.EquipDataRepository
import com.namu.core.rpg.equip.util.EquipInventoryManager
import com.namu.core.rpg.equip.util.EquipItemManager
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class EquipMainInventory : EquipInventoryManager() {

    override fun openInv(player: Player) {
        val inv = Bukkit.createInventory(null, 27, "Equip")
        for (i in 1..53)
            inv.setItem(i, EquipItemManager.GREENGLASS.itemStack)

        for (i in 0..3)
            inv.setItem(9 * i + 13, ItemStack(Material.AIR))

        for (i in 0..2){
            inv.setItem(9 * i + 30 , EquipItemManager.BROWNGLASS.itemStack)
            inv.setItem(9 * i + 32, EquipItemManager.BROWNGLASS.itemStack)
        }

        inv.setItem(49, EquipItemManager.BROWNGLASS.itemStack)

    }

    fun loadData(player: Player, inv: Inventory) {
        val data = EquipDataRepository.getEquip(player.name) ?: return
        inv.setItem(13, ItemStack(data.helmet))
        inv.setItem(22, ItemStack(data.chest))
        inv.setItem(23, ItemStack(data.weapon))
        inv.setItem(31, ItemStack(data.pants))
        inv.setItem(40, ItemStack(data.boots))
    }

}