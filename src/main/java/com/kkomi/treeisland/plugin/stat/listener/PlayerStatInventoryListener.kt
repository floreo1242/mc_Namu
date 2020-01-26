package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.stat.inventory.PlayerStatInventory
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.StatConfigRepository
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import com.kkomi.treeisland.plugin.stat.model.entity.StatConfig
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory

class PlayerStatInventoryListener : Listener {


    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val playerInfo = (event.player as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
            return
        }

        playerInfo.statInfo.updateFinalStat(playerInfo.equipmentInfo)
        playerInfo.statInfo.calculateStatOption(playerInfo.player)
    }

    @EventHandler
    fun onInventoryOpenEvent(event: InventoryOpenEvent) {
        val playerInfo = (event.player as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
            return
        }

        updateInventory(event.player as Player, inventory)
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val playerInfo = (event.whoClicked as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
            return
        }

        event.isCancelled = true

        val playerStat = playerInfo.statInfo
        getStatOptionBySlot(event.slot)?.let { upStat(playerStat, it) }
        updateInventory(event.whoClicked as Player, inventory)
    }

    private fun getStatOptionBySlot(slot: Int): StatOption? {
        return when (slot) {
            PlayerStatInventory.STRENGTH -> StatOption.STRENGTH
            PlayerStatInventory.DEXTERITY -> StatOption.DEXTERITY
            PlayerStatInventory.INTELLIGENCE -> StatOption.INTELLIGENCE
            PlayerStatInventory.AGILITY -> StatOption.AGILITY
            PlayerStatInventory.DEFENSE -> StatOption.DEFENSE
            else -> null
        }
    }

    private fun upStat(playerStat: PlayerStat, statOption: StatOption): Unit? {
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

    private fun updateInventory(player: Player, inventory: Inventory) {

        val statInfo = player.getPlayerInfo().statInfo

        inventory.setItem(PlayerStatInventory.HEAD, createItemStack(
                Material.SKULL_ITEM,
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
                        Material.BOOK,
                        "&c<< 힘 >>",
                        listOf(
                                "&f공격시 추가 데미지를 입힙니다.",
                                "&f크리티컬이 발생할 경우 효과가 미적용 됩니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatOption.STRENGTH]}"
                        )
                )
        )

        inventory.setItem(
                PlayerStatInventory.DEXTERITY,
                createItemStack(
                        Material.BOOK,
                        "&a<< 민첩 >>",
                        listOf(
                                "&f크리티컬 확률을 증가 시킵니다.",
                                "&f크리티컬 적중 시 두배의 데미지를 입힙니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatOption.DEXTERITY]}"
                        )
                )
        )

        inventory.setItem(
                PlayerStatInventory.INTELLIGENCE,
                createItemStack(
                        Material.BOOK,
                        "&b<< 지능 >>",
                        listOf(
                                "&f스킬 사용시 마나 소모량을 감소시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatOption.INTELLIGENCE]}"
                        )
                )
        )

        inventory.setItem(
                PlayerStatInventory.AGILITY,
                createItemStack(
                        Material.BOOK,
                        "&e<< 회피 >>",
                        listOf(
                                "&f피격 시 데미지를 무시하는 확률을 증가시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatOption.AGILITY]}"
                        )
                )
        )

        inventory.setItem(
                PlayerStatInventory.DEFENSE,
                createItemStack(
                        Material.BOOK,
                        "&7<< 방어 >>",
                        listOf(
                                "&f피격 시 받는 데미지를 감소시킵니다.",
                                "&f",
                                "&f투자 된 포인트 : &6${statInfo.investmentStat[StatOption.DEFENSE]}"
                        )
                )
        )

        player.updateInventory()

    }

}