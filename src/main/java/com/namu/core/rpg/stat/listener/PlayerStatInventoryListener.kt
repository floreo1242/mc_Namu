package com.namu.core.rpg.stat.listener

import com.kkomi.devlibrary.extension.createItemStack
import com.namu.core.rpg.stat.api.PlayerStatUpdateEvent
import com.namu.core.rpg.stat.inventory.PlayerStatInventory
import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.StatConfigRepository
import com.namu.core.rpg.stat.model.entity.PlayerStat
import com.namu.core.rpg.stat.util.playerStat
import com.namu.core.utility.itemdb.model.entity.StatType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.InventoryView

class PlayerStatInventoryListener : Listener {

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val inventory = event.view

        if (inventory.title != PlayerStatInventory.TITLE) {
            return
        }

        updateInventory(event.player as Player, inventory)
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        val inventory = event.view

        if (inventory.title != PlayerStatInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val playerStat = player.playerStat
        getStatOptionBySlot(event.slot)?.let { upStat(playerStat, it) }
        Bukkit.getPluginManager().callEvent(PlayerStatUpdateEvent(player))
        updateInventory(event.whoClicked as Player, inventory)
    }

    private fun getStatOptionBySlot(slot: Int): StatType? {
        return when (slot) {
            PlayerStatInventory.STRENGTH -> StatType.STRENGTH
            PlayerStatInventory.DEXTERITY -> StatType.HAND_DEXTERITY
            PlayerStatInventory.HEALTH -> StatType.HEALTH
            PlayerStatInventory.MANA -> StatType.MANA
            PlayerStatInventory.CRITICAL_CHANCE -> StatType.CRITICAL_CHANCE
            PlayerStatInventory.WALK_SPEED -> StatType.WALK_SPEED
            else -> null
        }
    }

    private fun upStat(playerStat: PlayerStat, statOption: StatType): Unit? {
        if (playerStat.leftPoint == 0) {
            return null
        }

        if (playerStat.investmentStat[statOption] == StatConfigRepository.getStatConfig().maxValue[statOption]) {
            return null
        }

        playerStat.investmentStat[statOption] = (playerStat.investmentStat[statOption] ?: 0) + 1
        playerStat.leftPoint -= 1
        PlayerStatRepository.editPlayerStat(playerStat)
        return Unit
    }

    private fun updateInventory(player: Player, inventory: InventoryView) {

        val statInfo = player.playerStat

        val statConfig = StatConfigRepository.getStatConfig()

        inventory.setItem(
                PlayerStatInventory.STRENGTH,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6힘 &a( ${statInfo.investmentStat[StatType.STRENGTH]} / ${statConfig.maxValue[StatType.STRENGTH]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.STRENGTH]} 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &5+${(statConfig.pointByValue[StatType.STRENGTH]
                                        ?: error("")) * (statInfo.investmentStat[StatType.STRENGTH] ?: 1)} 데미지"
                        ),
                        durability = 1
                )
        )

        inventory.setItem(
                PlayerStatInventory.HEALTH,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6체력 &a( ${statInfo.investmentStat[StatType.HEALTH]} / ${statConfig.maxValue[StatType.HEALTH]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.HEALTH]} 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &c+${(statConfig.pointByValue[StatType.HEALTH] ?: error("")) * (statInfo.investmentStat[StatType.HEALTH] ?: 1)} HP"
                        ),
                        durability = 2
                )
        )

        inventory.setItem(
                PlayerStatInventory.MANA,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6마력 &a( ${statInfo.investmentStat[StatType.MANA]} / ${statConfig.maxValue[StatType.MANA]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.MANA]} 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &b+${(statConfig.pointByValue[StatType.MANA] ?: error("")) * (statInfo.investmentStat[StatType.MANA] ?: 1)} MP"
                        ),
                        durability = 2
                )
        )

        inventory.setItem(
                PlayerStatInventory.WALK_SPEED,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6이동속도 &a( ${statInfo.investmentStat[StatType.WALK_SPEED]} / ${statConfig.maxValue[StatType.WALK_SPEED]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.WALK_SPEED]}% 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &e+${(statConfig.pointByValue[StatType.WALK_SPEED] ?: error("")) * (statInfo.investmentStat[StatType.WALK_SPEED] ?: 1)}%"
                        ),
                        durability = 2
                )
        )

        inventory.setItem(
                PlayerStatInventory.CRITICAL_CHANCE,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6치명타 확률 &a( ${statInfo.investmentStat[StatType.CRITICAL_CHANCE]} / ${statConfig.maxValue[StatType.CRITICAL_CHANCE]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.CRITICAL_CHANCE]}% 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &e+${(statConfig.pointByValue[StatType.CRITICAL_CHANCE] ?: error("")) * (statInfo.investmentStat[StatType.CRITICAL_CHANCE] ?: 1)}%"
                        ),
                        durability = 2
                )
        )

        inventory.setItem(
                PlayerStatInventory.DEXTERITY,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&6손재주 &a( ${statInfo.investmentStat[StatType.HAND_DEXTERITY]} / ${statConfig.maxValue[StatType.HAND_DEXTERITY]} )",
                        listOf(
                                "&f1 포인트 당 +${statConfig.pointByValue[StatType.HAND_DEXTERITY]}% 증가",
                                "&f남은 스텟포인트 : &e${statInfo.leftPoint}",
                                "&f증가량 : &e+${(statConfig.pointByValue[StatType.HAND_DEXTERITY] ?: error("")) * (statInfo.investmentStat[StatType.HAND_DEXTERITY] ?: 1)}% 성공확률 및 제작시간 감소"
                        ),
                        durability = 2
                )
        )

        player.updateInventory()

    }

}