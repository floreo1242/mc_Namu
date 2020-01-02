package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.plugin.money.model.MoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import org.bukkit.OfflinePlayer

class OfflinePlayerInfo(
        val offlinePlayer: OfflinePlayer
) {
    private val uuid: String = offlinePlayer.uniqueId.toString()
    val moneyInfo: PlayerMoney = MoneyRepository.getPlayerMoney(uuid)!!
    val statInfo: PlayerStat = PlayerStatRepository.getPlayerStat(uuid)!!
    val questInfo: PlayerQuest = PlayerQuestRepository.getPlayerQuest(uuid)!!
}