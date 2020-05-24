package com.namu.core.rpg.equip.inventory

import com.kkomi.devlibrary.extension.createItemStack
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

        val WEAPON_SLOT = createItemStack(
                Material.ITEM_FRAME,
                "&f무기 슬롯",
                listOf(
                        "&f장비창에 장착한 무기가 출력됩니다.",
                        "&f외형을 장착 할 경우 외형이 우선적으로 적용되며 옵션은 동일하게 작동합니다."
                )
        )
    }

    override val inventory: Inventory = Bukkit.createInventory(null, 54, TITLE)

    override fun setBasicFrame() {
        for (i in 0..53) {
            inventory.setItem(i, ItemStack(Material.BLACK_STAINED_GLASS_PANE))
        }

        val playerEquipInfo = PlayerEquipInfoRepository.getPlayerEquipInfo(player.uniqueId.toString())!!

        EquipmentType.values().forEach { inventory.setItem(it.index, playerEquipInfo.items[it]) }
    }

}