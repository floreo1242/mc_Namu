package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.money.model.MoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid : String = player.uniqueId.toString()
    val moneyInfo : PlayerMoney = MoneyRepository.getPlayerMoney(uuid)!!
    val questInfo : PlayerQuest = PlayerQuestRepository.getPlayerQuest(uuid)!!

    fun sendErrorMessage(message : Any) {
        player.sendErrorMessage(message)
    }
    fun sendInfoMessage(message : Any) {
        player.sendInfoMessage(message)
    }
    fun sendDebugMessage(message : Any) {
        player.sendDebugMessage(message)
    }
}