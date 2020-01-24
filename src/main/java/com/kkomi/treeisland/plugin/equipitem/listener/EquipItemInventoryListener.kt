package com.kkomi.treeisland.plugin.equipitem.listener

import com.kkomi.treeisland.library.extension.emptyCount
import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentType
import com.kkomi.treeisland.plugin.equipitem.inventory.EquipItemInventory
import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack

class EquipItemInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != EquipItemInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val currentItem = event.currentItem

        if (currentItem == null || currentItem.type == Material.AIR || currentItem.getDisplay() == null) {
            return
        }

        val equipmentItem = EquipmentItemRepository.getItemFromItemDisplay(currentItem.getDisplay()!!) ?: return
        val equipmentSlot = getSlotFromEquipmentType(equipmentItem.equipmentType)
        val slot = event.rawSlot

        when (isClickArea(event.inventory.size, slot)) {
            InventoryArea.TOP -> {
                // 아이템 장착 슬롯인가?
                if (!EquipItemInventory.equipItemSlotList.contains(slot)) {
                    return
                }

                // 잔여 슬롯이 있는가?
                if (inventory.storageContents.emptyCount() == 0) {
                    return
                }

                val equipmentInventoryItem = inventory.getItem(slot)
                inventory.setItem(slot, ItemStack(Material.IRON_BARDING))
                playerInfo.player.inventory.addItem(equipmentInventoryItem)
                equipmentInventoryItem.amount = 0
            }
            InventoryArea.BOTTOM -> {
                val item = inventory.getItem(equipmentSlot)

                if (item == null || item.type == Material.AIR || item.type == Material.IRON_BARDING) {
                    inventory.setItem(equipmentSlot, currentItem)
                    currentItem.amount = 0
                } else {
                    playerInfo.sendErrorMessage("장착중인 아이템을 제거 후 착용해주세요!")
                }
            }
        }
    }

    @EventHandler
    fun onPlayerInventoryClickEvent(event: InventoryClickEvent) {
        // is slot 0?
        if (event.slot != 0) {
            return
        }

        // is Creative Inventory?
        if (event.clickedInventory == null) {
            return
        }
        // Clicked Inventory Type is Player?
        if (event.clickedInventory.type != InventoryType.PLAYER) {
            return
        }

        // is Equip Weapon?
        if (PlayerEquipItemRepository.getPlayerEquipItem(event.whoClicked.uniqueId.toString())!!.weapon.type == Material.AIR) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
        val playerWeapon = event.player.getPlayerInfo().equipmentInfo.weapon
        if (playerWeapon.type != Material.AIR && event.player.inventory.heldItemSlot == 0) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val playerInfo = (event.player as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != EquipItemInventory.TITLE) {
            return
        }

        PlayerEquipItemRepository.editPlayerEquipItem(
                playerInfo.equipmentInfo.apply {
                    weapon = inventory.getItem(EquipItemInventory.WEAPON) ?: ItemStack(Material.IRON_BARDING)
                    helmet = inventory.getItem(EquipItemInventory.HELMET) ?: ItemStack(Material.AIR)
                    plate = inventory.getItem(EquipItemInventory.PLATE) ?: ItemStack(Material.AIR)
                    legging = inventory.getItem(EquipItemInventory.LEGGINGS) ?: ItemStack(Material.AIR)
                    boots = inventory.getItem(EquipItemInventory.BOOTS) ?: ItemStack(Material.AIR)
                }
        )

        playerInfo.statInfo.updateFinalStat(playerInfo.equipmentInfo)
        playerInfo.statInfo.calculateStatOption(playerInfo.player)
        PlayerStatRepository.editPlayerStat(playerInfo.statInfo)

        applyWeaponInPlayerInventory(playerInfo.player)
    }

    private fun getSlotFromEquipmentType(equipmentType: EquipmentType): Int {
        return when (equipmentType) {
            EquipmentType.WEAPON -> EquipItemInventory.WEAPON
            EquipmentType.HELMET -> EquipItemInventory.HELMET
            EquipmentType.PLATE -> EquipItemInventory.PLATE
            EquipmentType.LEGGINGS -> EquipItemInventory.LEGGINGS
            EquipmentType.BOOTS -> EquipItemInventory.BOOTS
        }
    }

    private fun applyWeaponInPlayerInventory(player: Player) {
        val playerInfo = player.getPlayerInfo()
        player.inventory.setItem(0, playerInfo.equipmentInfo.weapon)
    }

    private fun isClickArea(inventorySize: Int, rowId: Int): InventoryArea {
        return if (rowId < inventorySize) {
            InventoryArea.TOP
        } else {
            InventoryArea.BOTTOM
        }
    }

    enum class InventoryArea { TOP, BOTTOM }

}