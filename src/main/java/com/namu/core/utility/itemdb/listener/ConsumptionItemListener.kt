package com.namu.core.utility.itemdb.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.utility.itemdb.model.entity.CustomItem
import com.namu.core.utility.lib.performCommand
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*

class ConsumptionItemListener : Listener {

    private val itemCooldown: MutableMap<Player, MutableMap<CustomItem, Long>> = mutableMapOf()

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        if (itemCooldown[event.player] == null) {
            itemCooldown[event.player] = mutableMapOf()
        }
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player
        val itemStack = event.item

        if (itemStack == null || itemStack.type == Material.AIR || !itemStack.hasItemMeta() || !itemStack.itemMeta!!.hasDisplayName()) {
            return
        }

        val currentTime = Calendar.getInstance()
        val customItem = itemStack.getNBTTagCompound(CustomItem::class.java) ?: return
        val consumptionOption = customItem.consumptionOption ?: return

        if (itemCooldown[event.player] == null) {
            itemCooldown[event.player] = mutableMapOf()
        }

        if (itemCooldown[player]!![customItem] != null) {
            val leftTime = itemCooldown[player]!![customItem]!! - currentTime.timeInMillis
            if (leftTime > 0) {
                player.sendErrorMessage("아이템 재사용시간까지 ${leftTime / 1000}초 남았습니다.")
                return
            }
        }

        player.performCommand(consumptionOption.command, true)

        itemStack.amount -= 1
        itemCooldown[player]!![customItem] = Calendar.getInstance().apply { add(Calendar.SECOND, consumptionOption.cooldown) }.timeInMillis
    }

}