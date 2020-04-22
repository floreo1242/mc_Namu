package com.namu.core.rpg.equip.listener

import com.namu.core.rpg.equip.model.EquipDataRepository
import com.namu.core.rpg.equip.util.EquipItemManager
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack


class EquipMainInvListener : Listener {

    @EventHandler
    fun mainClickEv(event : InventoryClickEvent) {
        val player : Player = event.whoClicked as Player
        if(ChatColor.stripColor(player.openInventory.title).equals("equip",ignoreCase = true)) {
            event.setCancelled(true)
            val currentitem = event.currentItem
            if(currentitem == null || currentitem.type == Material.AIR) {
                event.setCancelled(true)
            } else if (checkWearble(currentitem)){
                event.setCancelled(true)
            }
        }
    }

    @EventHandler
    fun mainCloseEv(event: InventoryCloseEvent) {
        val player : Player = event.player as Player
        if(ChatColor.stripColor(player.openInventory.title).equals("equip", ignoreCase = true)){
            val inv = player.openInventory
            for(i in 0..3){
                if(inv.getItem(4 + 9 * i) != null) {
                    System.out.println("데이터 보낸 장비 : " + inv.getItem(4 + 9 * i))
                    when(i) {
                        0 -> sendData(player, "head", inv.getItem(4 + 9 * i) as ItemStack)
                        1 -> sendData(player, "chest", inv.getItem(4 + 9 * i) as ItemStack)
                        2 -> sendData(player, "leggings", inv.getItem(4 + 9 * i) as ItemStack)
                        3 -> sendData(player, "boots", inv.getItem(4 + 9 * i) as ItemStack)
                    }

                    if (i == 1) {
                        sendData(player, "weapon", inv.getItem(5 + 9 * i) as ItemStack)
                        System.out.println("데이터 보낸 장비 : " + inv.getItem(5 + 9 * i))
                    }
                }
            }


        }


    }

    fun checkWearble(item : ItemStack) : Boolean{
        when (item){
            EquipItemManager.DIAMOND_HELMET.itemStack -> {
                return true
            }
            EquipItemManager.DIAMOND_CHESTPLATE.itemStack -> {
                return true
            }

            EquipItemManager.DIAMOND_BOOTS.itemStack -> {
                return true
            }

            EquipItemManager.DIAMOND_LEGGINGS.itemStack -> {
                return true
            }
        }
        return false
    }

    fun sendData(player: Player, type:String, item: ItemStack){
        val data = EquipDataRepository.getEquip(player.name) ?: return
        when(type) {
            "helmet" -> data.helmet = item.type
            "chest" -> data.chest = item.type
            "leggings" -> data.pants = item.type
            "boots" -> data.boots = item.type
        }
    }

    fun applyData(player: Player) {

    }

}