package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.library.extension.getDisplay
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.EquipmentItemRepository
import com.kkomi.treeisland.plugin.stat.model.AttackSpeedRepository
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.util.*

class PlayerStatListener : Listener {

    private val attackSpeedMap = mutableMapOf<String, Long>()

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId.toString()

        if (PlayerStatRepository.getPlayerStat(uuid) == null) {
            PlayerStatRepository.createPlayerStat(uuid)
        }
    }

    @EventHandler
    fun onPlayerDamageEvent(event: EntityDamageByEntityEvent) {

        if (event.damager !is Player) {
            return
        }

        val playerInfo = (event.damager as Player).getPlayerInfo()
        val longTime = Calendar.getInstance().timeInMillis

        if (longTime < attackSpeedMap[playerInfo.player.uniqueId.toString()] ?: 0) {
            event.isCancelled = true
            return
        }

        val itemDisplay = playerInfo.statInfo.equipItem.weapon.getDisplay() ?: return
        val equipmentItem = EquipmentItemRepository.getItemFromItemDisplay(itemDisplay) ?: return

        event.damage = playerInfo.statInfo.getDamage()
        attackSpeedMap[playerInfo.player.uniqueId.toString()] = Calendar.getInstance().timeInMillis + (AttackSpeedRepository.getAttackSpeed(equipmentItem.equipmentSubType) * 1000).toLong()

        playerInfo.sendInfoMessage("${event.entity}님에게 총 ${event.damage.toInt()}만큼의 피해를 주었습니다.")
    }

}