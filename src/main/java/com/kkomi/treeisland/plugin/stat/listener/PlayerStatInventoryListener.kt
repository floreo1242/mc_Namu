package com.kkomi.treeisland.plugin.stat.listener

import com.kkomi.treeisland.library.extension.createItemStack
import com.kkomi.treeisland.library.extension.getServerTitleInfo
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import com.kkomi.treeisland.plugin.stat.inventory.PlayerStatInventory
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class PlayerStatInventoryListener : Listener {

    @EventHandler
    fun onInventoryCloseEvent(event: InventoryCloseEvent) {
        val playerInfo = (event.player as Player).getPlayerInfo()
        val inventory = event.inventory
        val data = inventory.getServerTitleInfo() ?: return

        if (data.first != PlayerStatInventory.TITLE) {
            return
        }

        playerInfo.statInfo.updateFinalStat()
        playerInfo.statInfo.applyFinalStat(playerInfo.player)
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

        when (event.slot) {
            PlayerStatInventory.STRENGTH -> upStat(playerStat, StatOption.STRENGTH)
                    ?: playerInfo.sendErrorMessage("잔여 포인트가 부족합니다!")
            PlayerStatInventory.MIND -> upStat(playerStat, StatOption.MIND)
                    ?: playerInfo.sendErrorMessage("잔여 포인트가 부족합니다!")
            PlayerStatInventory.NATURE -> upStat(playerStat, StatOption.NATURE)
                    ?: playerInfo.sendErrorMessage("잔여 포인트가 부족합니다!")
            PlayerStatInventory.STAMINA -> upStat(playerStat, StatOption.STAMINA)
                    ?: playerInfo.sendErrorMessage("잔여 포인트가 부족합니다!")
            else -> return
        }

        inventory.setItem(PlayerStatInventory.HEAD, createItemStack(
                Material.SKULL_ITEM,
                "${playerInfo.player.name}님의 스텟 정보",
                listOf(
                        *playerStat.pickingStat.map {
                            "&f${it.key.strName} : ${it.value}"
                        }.toTypedArray(),
                        "",
                        "&f잔여 포인트 : ${playerStat.leftPoint}"
                ),
                durability = 3))
    }

    private fun upStat(playerStat: PlayerStat, statOption: StatOption): Unit? {
        if (playerStat.leftPoint == 0) {
            return null
        }
        playerStat.pickingStat[statOption] = (playerStat.pickingStat[statOption] ?: 0) + 1
        PlayerStatRepository.editPlayerStat(playerStat)
        return Unit
    }

}