package com.namu.core.rpg.equip.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.equip.inventory.EquipInventory
import com.namu.core.rpg.equip.model.PlayerEquipInfoRepository
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.utility.itemdb.model.EquipmentType
import com.namu.core.utility.itemdb.model.entity.EquipmentOption
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class EquipInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {

        val inventory = event.clickedInventory as InventoryView

        if (inventory.title == EquipInventory.TITLE) {
            return
        }

        val player = event.whoClicked as Player
        val cursorItem = event.cursor ?: return
        val clickedSlot = event.slot
        val equipmentOption = cursorItem.getNBTTagCompound(EquipmentOption::class.java)

        // 손에 든 아이템이 장비아이템이 아닐 경우
        if (equipmentOption == null) {
            player.sendErrorMessage("장비아이템만 장착이 가능합니다.")
            event.isCancelled = true
            return
        }

        // 반지 인 경우
        val isCorrectEquipmentItem = if (equipmentOption.equipType == EquipmentType.RING) {
            EquipmentType.RING.index == clickedSlot || EquipmentType.RING_SUB.index == clickedSlot
        } else { // 반지가 아닌 경우
            equipmentOption.equipType.index != clickedSlot
        }

        // 손에 든 장비아이템의 타입이 슬롯이랑 일치하지 않을 경우
        if (isCorrectEquipmentItem) {
            player.sendErrorMessage("해당 슬롯에는 착용 할 수 없는 아이템 입니다.")
            event.isCancelled = true
            return
        }

        // 플레이어의 레벨이 착용 하려는 아이템의 레벨보다 낮을 경우
        if (equipmentOption.levelLimit > player.playerLevel.level) {
            player.sendErrorMessage("착용하려는 아이템의 레벨이 현재 레벨보다 높습니다.")
            event.isCancelled = true
            return
        }

        // 아이템 장착
        val playerStatus = PlayerStatusRepository.getPlayerStatus(player)
        playerStatus.calculate()
        playerStatus.apply()
    }

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {

        val inventory = event.inventory as InventoryView

        if (inventory.title == EquipInventory.TITLE) {
            return
        }

        val player = event.player as Player

        val playerEquipInfo = PlayerEquipInfoRepository.getPlayerEquipInfo(player.uniqueId.toString()) ?: return

        playerEquipInfo.items = EquipmentType.values().map {
            it to (inventory.getItem(it.index) ?: ItemStack(Material.AIR))
        }.toMap()

        PlayerEquipInfoRepository.savePlayerEquipInfo(playerEquipInfo)

    }

}