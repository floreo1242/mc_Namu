package com.namu.core.rpg.enhance.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.rpg.enhance.inventory.EnhanceInventory
import com.namu.core.rpg.enhance.model.EnhanceItemMeta
import com.namu.core.rpg.enhance.model.EnhanceStone
import com.namu.core.utility.itemdb.model.entity.CustomItem
import com.namu.core.utility.itemdb.util.refreshEquipmentItemLore
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import kotlin.random.Random
import kotlin.random.nextInt

class EnhanceInventoryListener : Listener {

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val inventory = event.view

        if (inventory.title != EnhanceInventory.TITLE) {
            return
        }

        val player = event.whoClicked as Player
        val cursorItem = event.cursor ?: return

        if (cursorItem.type == Material.AIR) {
            return
        }

        // 커서 아이템이 강화석이 아니면 종료
        val enhanceStone = cursorItem.getNBTTagCompound(EnhanceStone::class.java) ?: return
        val currentItem = event.currentItem ?: return

        if (currentItem.type == Material.AIR) {
            return
        }

        event.isCancelled = true

        val customItem = currentItem.getNBTTagCompound(CustomItem::class.java)

        // 장비아이템이 아닌 아이템을 클릭 했을 경우
        if (customItem == null) {
            player.sendErrorMessage("장비아이템에만 강화가 가능합니다.")
            return
        }

        // 장비아이템이 아닌 아이템을 클릭 했을 경우
        if (customItem.equipmentOption == null) {
            player.sendErrorMessage("장비아이템에만 강화가 가능합니다.")
            return
        }

        if (event.slot == 0) {
            player.sendErrorMessage("착용중인 아이템에는 강화가 불가능 합니다.")
            return
        }

        // 기존에 강화석 옵션이 있다면 가져오고 없다면 새로 추가
        val enhanceItemMeta = currentItem.getNBTTagCompound(EnhanceItemMeta::class.java)
                ?: EnhanceItemMeta(5, mutableListOf())

        if (enhanceItemMeta.scrollLimit == 0) {
            player.sendErrorMessage("더 이상 강화가 불가능합니다.")
            return
        }

        // 장비아이템을 클릭했을 경우
        event.currentItem = currentItem.run {
            if (enhanceStone.chance >= Random.nextInt(1..100)) {
                enhanceItemMeta.apply {
                    scrollLimit -= 1
                    scrollOptions.add(enhanceStone.statOption to Random.nextInt(enhanceStone.minValue..enhanceStone.maxValue))
                }
                player.sendInfoMessage("강화에 &9성공&f하였습니다.")
            } else {
                enhanceItemMeta.apply {
                    scrollLimit -= 1
                }
                player.sendInfoMessage("강화에 &c실패&f하였습니다.")
            }
            addNBTTagCompound(enhanceItemMeta)
        }.apply {
            refreshEquipmentItemLore()
        }

        // 강화석 삭제
        val cursorEnhanceStone = event.cursor ?: return

        cursorEnhanceStone.amount -= 1

    }

}