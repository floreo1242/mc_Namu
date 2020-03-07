package com.kkomi.treeisland.plugin.enhance.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.addNBTTagCompound
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.treeisland.plugin.enhance.inventory.EnhanceInventory
import com.kkomi.treeisland.plugin.enhance.model.EnhanceItemMeta
import com.kkomi.treeisland.plugin.enhance.model.EnhanceStone
import com.kkomi.treeisland.plugin.itemdb.model.entity.EquipmentItem
import com.kkomi.treeisland.plugin.itemdb.util.refreshEquipmentItemLore
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
        val inventory = event.inventory

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

        // 장비아이템이 아닌 아이템을 클릭 했을 경우
        if (currentItem.getNBTTagCompound(EquipmentItem::class.java) == null) {
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
            if (enhanceStone.chance >= Random.nextInt(1..100)){
                enhanceItemMeta.apply {
                    scrollLimit -= 1
                    scrollOptions.add(enhanceStone.statOption to Random.nextInt(enhanceStone.minValue..enhanceStone.maxValue))
                }
                player.sendInfoMessage("강화에 &9성공&f하였습니다.")
            }else {
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
        event.cursor.amount -= 1

    }

}