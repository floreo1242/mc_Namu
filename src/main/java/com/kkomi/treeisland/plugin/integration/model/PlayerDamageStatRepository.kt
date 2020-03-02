package com.kkomi.treeisland.plugin.integration.model

import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption
import org.bukkit.entity.Player

object PlayerDamageStatRepository {
    private val playerByFinalStat = mutableMapOf<Player, Map<StatOption, Double>>()

    fun getPlayerFinalStat(player: Player): Map<StatOption, Double> {
        return playerByFinalStat[player] ?: mapOf()
    }

    fun setPlayerFinalStat(player: Player) {
        val finalStat = mutableMapOf<StatOption, Double>()

        with(player.getPlayerInfo()) {
            // 착용아이템 옵션
            equipmentInfo.getEquipmentItemStat()
                    .forEach {
                        finalStat[it.key] = (finalStat[it.key] ?: 0.0) + it.value
                    }

            // 플레이어 스텟 옵션
            statInfo.investmentStat
                    .forEach {
                        finalStat[it.key] = (finalStat[it.key] ?: 0.0) + it.value
                    }
        }

        playerByFinalStat[player] = finalStat
    }
}