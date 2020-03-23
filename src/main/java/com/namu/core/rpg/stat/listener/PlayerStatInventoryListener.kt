package com.namu.core.rpg.stat.listener

import com.kkomi.devlibrary.extension.createItemStack
import com.kkomi.devlibrary.extension.getServerTitleInfo
import com.namu.core.utility.itemdb.model.entity.StatType
import com.namu.core.rpg.stat.api.PlayerStatUpdateEvent
import com.namu.core.rpg.stat.inventory.PlayerStatInventory
import com.namu.core.rpg.stat.model.PlayerStatRepository
import com.namu.core.rpg.stat.model.StatConfigRepository
import com.namu.core.rpg.stat.model.entity.PlayerStat
import com.namu.core.rpg.stat.util.playerStat
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
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
            return
        }

        updateInventory(event.player as Player, inventory)
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        val inventory = event.view
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
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
            PlayerStatInventory.DEXTERITY -> StatType.DEXTERITY
            PlayerStatInventory.INTELLIGENCE -> StatType.INTELLIGENCE
            PlayerStatInventory.AGILITY -> StatType.AGILITY
            PlayerStatInventory.DEFENSE -> StatType.VITALITY
            else -> null
        }
    }

    private fun upStat(playerStat: PlayerStat, statOption: StatType): Unit? {
        if (playerStat.leftPoint == 0) {
            return null
        }

        if (playerStat.investmentStat[statOption] == StatConfigRepository.getStatConfig().statLimit) {
            return null
        }

        playerStat.investmentStat[statOption] = (playerStat.investmentStat[statOption] ?: 0) + 1
        playerStat.leftPoint -= 1
        PlayerStatRepository.editPlayerStat(playerStat)
        return Unit
    }

    private fun updateInventory(player: Player, inventory: InventoryView) {

        val statInfo = player.playerStat

        inventory.setItem(PlayerStatInventory.HEAD, createItemStack(
                Material.SKELETON_SKULL,
                "${player.name}님의 스텟 정보",
                listOf(
                        *statInfo.investmentStat.map {
                            "&f${it.key.strName} : ${it.value}"
                        }.toTypedArray(),
                        "",
                        "&f잔여 포인트 : ${statInfo.leftPoint}"
                ),
                durability = 3))

        inventory.setItem(
                PlayerStatInventory.STRENGTH,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&c<< 힘 >>",
                        listOf(
                                "&f공격시 추가 데미지를 입힙니다.",
                                "&f크리티컬이 발생할 경우 효과가 미적용 됩니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatType.STRENGTH]}"
                        ),
                        durability = 1
                )
        )

        inventory.setItem(
                PlayerStatInventory.DEXTERITY,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&a<< 민첩 >>",
                        listOf(
                                "&f크리티컬 확률을 증가 시킵니다.",
                                "&f크리티컬 적중 시 두배의 데미지를 입힙니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatType.DEXTERITY]}"
                        ),
                        durability = 2
                )
        )

        inventory.setItem(
                PlayerStatInventory.INTELLIGENCE,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&b<< 지능 >>",
                        listOf(
                                "&f스킬 사용시 마나 소모량을 감소시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatType.INTELLIGENCE]}"
                        ),
                        durability = 4
                )
        )

        inventory.setItem(
                PlayerStatInventory.AGILITY,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&e<< 회피 >>",
                        listOf(
                                "&f피격 시 데미지를 무시하는 확률을 증가시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatType.AGILITY]}"
                        ),
                        durability = 3
                )
        )

        inventory.setItem(
                PlayerStatInventory.DEFENSE,
                createItemStack(
                        Material.GOLDEN_SHOVEL,
                        "&7<< 방어 >>",
                        listOf(
                                "&f피격 시 받는 데미지를 감소시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatType.VITALITY]}"
                        ),
                        durability = 5
                )
        )

        player.updateInventory()

    }

}