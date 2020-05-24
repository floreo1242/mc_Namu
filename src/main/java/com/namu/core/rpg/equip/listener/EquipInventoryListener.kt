package com.namu.core.rpg.equip.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.equip.inventory.EquipInventory
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.utility.itemdb.model.EquipmentType
import com.namu.core.utility.itemdb.model.entity.CustomItem
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack

class EquipInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.view

        if (inventory.title != EquipInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val player = event.whoClicked as Player
        val clickedItem = event.currentItem
        val clickedSlot = event.rawSlot
        val equipmentOption = clickedItem?.getNBTTagCompound(CustomItem::class.java)?.equipmentOption

        println("""
            
                    clickedItem: $clickedItem,
                    clickedSlot: $clickedSlot,
                    equipmentOption: $equipmentOption
                """.trimIndent())

        if (clickedSlot < 54) {
            println("// 클릭 한 위치가 장비창 인벤토리 일 경우 - 아이템 탈착")

            if (!EquipmentType.values().map { it.index }.contains(clickedSlot)) {
                println("// 슬롯이 아닌 곳을 클릭 할 경우")
                return
            }

            if (player.inventory.storageContents.count { it == null || it.type.isAir } == 0) {
                println("// 아이템 해제 시 인벤토리에 공간이 없는 경우")
                player.sendErrorMessage("인벤포리에 빈 공간이 없습니다.")
                return
            }

            inventory.setItem(clickedSlot, null)
            player.inventory.addItem(clickedItem)

        } else {
            println("// 클릭 한 위치가 유저 인벤토리 일 경우 - 아이템 장착")

            if (equipmentOption == null) {
                println("// 클릭 한 아이템 장비아이템이 아닐 경우")
                player.sendErrorMessage("장비아이템만 장착이 가능합니다.")
                return
            }

            val equipSlotItem = inventory.getItem(equipmentOption.equipType.index)

            if (equipSlotItem != null && !equipSlotItem.type.isAir) {
                println("// 아이템 슬롯에 이미 아이템을 장착하고 있을 경우")
                player.sendErrorMessage("장착된 아이템을 탈착하고 착용해주세요.")
                return
            }

            if (equipmentOption.levelLimit > player.playerLevel.level) {
                println("// 플레이어의 레벨이 착용 하려는 아이템의 레벨보다 낮을 경우")
                player.sendErrorMessage("착용하려는 아이템의 레벨이 현재 레벨보다 높습니다.")
                return
            }

            // 아이템 장착
            inventory.setItem(equipmentOption.equipType.index, clickedItem.clone())
            event.currentItem = null

        }

        val playerStatus = PlayerStatusRepository.getPlayerStatus(player)
        playerStatus.calculate()
        playerStatus.apply()

        // weapon design
        val weapon = inventory.getItem(EquipmentType.WEAPON.index)
        val weaponOutPoly = inventory.getItem(EquipmentType.WEAPON_OUT_POLY.index)

        // 외형을 가지고 있을 경우
        if (weaponOutPoly != null && !weaponOutPoly.type.isAir) {
            player.inventory.setItem(0, weaponOutPoly)
            return
        }

        // 무기를 착용하고 있는 경우
        if (weapon != null && !weapon.type.isAir) {
            player.inventory.setItem(0, weapon)
            return
        }

        // 아무것도 착용하지 않고 있는 경우
        player.inventory.setItem(0, EquipInventory.WEAPON_SLOT)
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {

        val inventory = event.view

        if (inventory.title != EquipInventory.TITLE) {
            return
        }

        val player = event.player as Player

        val playerEquipInfo = PlayerEquipInfoRepository.getPlayerEquipInfo(player.uniqueId.toString()) ?: return

        playerEquipInfo.items = EquipmentType.values().map {
            it to (inventory.getItem(it.index) ?: ItemStack(Material.AIR))
        }.toMap()

        PlayerEquipInfoRepository.savePlayerEquipInfo(playerEquipInfo)

        val playerStatus = PlayerStatusRepository.getPlayerStatus(player)
        playerStatus.calculate()
        playerStatus.apply()

    }

}