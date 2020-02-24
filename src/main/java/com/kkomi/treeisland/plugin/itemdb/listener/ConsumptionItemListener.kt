package com.kkomi.treeisland.plugin.itemdb.listener

import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.kkomi.treeisland.plugin.itemdb.model.ConsumptionItemRepository
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItem
import com.kkomi.treeisland.plugin.itemdb.model.entity.ConsumptionItemType
import com.nisovin.magicspells.MagicSpells
import com.nisovin.magicspells.mana.ManaChangeReason
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*

class ConsumptionItemListener : Listener {

    private val itemCooldown: MutableMap<Player, MutableMap<ConsumptionItem, Long>> = mutableMapOf()

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

        if (itemStack == null || itemStack.type == Material.AIR || !itemStack.hasItemMeta() || !itemStack.itemMeta.hasDisplayName()) {
            return
        }

        val currentTime = Calendar.getInstance()
        val consumptionItem = itemStack.getNBTTagCompound(ConsumptionItem::class.java) ?: return

        if (itemCooldown[event.player] == null) {
            itemCooldown[event.player] = mutableMapOf()
        }

        if (itemCooldown[player]!![consumptionItem] != null) {
            val leftTime = itemCooldown[player]!![consumptionItem]!! - currentTime.timeInMillis
            if (leftTime > 0) {
                player.sendErrorMessage("아이템 재사용시간까지 ${leftTime / 1000}초 남았습니다.")
                return
            }
        }

        when (consumptionItem.type) {
            ConsumptionItemType.HEALTH -> {
                if (player.health + consumptionItem.value >= player.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue) {
                    player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue
                } else {
                    player.health += consumptionItem.value
                }
                player.sendInfoMessage("체력을 &6${consumptionItem.value}&f만큼 회복하였습니다.")
            }
            ConsumptionItemType.MANA -> {
                MagicSpells.getManaHandler().addMana(player, consumptionItem.value, ManaChangeReason.POTION)
                player.sendInfoMessage("마나를 &6${consumptionItem.value}&f만큼 회복하였습니다.")
            }
            ConsumptionItemType.STAT -> {

            }
        }

        itemStack.amount -= 1
        itemCooldown[player]!![consumptionItem] = Calendar.getInstance().apply { add(Calendar.SECOND, consumptionItem.cooldown) }.timeInMillis
    }

}