package com.namu.core.economy.money.listener

import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.devlibrary.nms.getNBTTagCompound
import com.namu.core.economy.money.model.PlayerMoneyRepository
import com.namu.core.economy.money.model.entity.CheckPaper
import com.namu.core.economy.money.model.entity.PlayerMoney
import com.namu.core.economy.money.util.edit
import com.namu.core.economy.money.util.playerMoney
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent

class PlayerMoneyListener : Listener {
    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        val player = event.player
        val playerMoney = PlayerMoneyRepository.getPlayerMoney(player.uniqueId.toString())

        if (playerMoney == null) {
            PlayerMoneyRepository.createPlayerMoney(PlayerMoney(player.uniqueId.toString(), 0))
            return
        }
    }

    @EventHandler
    fun onPlayerInteractMoney(event: PlayerInteractEvent) {
        val player = event.player
        val itemStack = event.item ?: return

        val checkPaper = itemStack.getNBTTagCompound(CheckPaper::class.java) ?: return

        println(checkPaper)

        player.playerMoney.apply { money += checkPaper.price }.edit()
        itemStack.amount -= 1
        player.sendInfoMessage("수표를 사용하여 돈을 획득 하였습니다.")
    }
}