package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid: String = player.uniqueId.toString()
    val moneyInfo: PlayerMoney = PlayerMoneyRepository.getPlayerMoney(uuid)!!
    val questInfo: PlayerQuest = PlayerQuestRepository.getPlayerQuest(uuid)!!
    val statInfo: PlayerStat = PlayerStatRepository.getPlayerStat(uuid)!!
    val levelInfo: PlayerLevel = PlayerLevelRepository.getPlayerLevel(uuid)!!
    val guildInfo: PlayerGuild = PlayerGuildRepository.getPlayerGuild(uuid)!!
    val equipmentInfo : PlayerEquipItem = PlayerEquipItemRepository.getPlayerEquipItem(uuid)!!
    val roleInfo : PlayerRole = PlayerRoleRepository.getPlayerRole(uuid)!!

    fun editPlayerInfo() {
        PlayerMoneyRepository.editPlayerMoney(moneyInfo)
        PlayerQuestRepository.editPlayerQuest(questInfo)
        PlayerStatRepository.editPlayerStat(statInfo)
        PlayerLevelRepository.editPlayerLevel(levelInfo)
    }

    fun sendErrorMessage(message: Any) {
        player.sendErrorMessage(message)
    }

    fun sendInfoMessage(message: Any) {
        player.sendInfoMessage(message)
    }

    fun sendDebugMessage(message: Any) {
        player.sendDebugMessage(message)
    }
}