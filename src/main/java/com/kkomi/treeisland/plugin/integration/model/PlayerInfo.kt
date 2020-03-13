package com.kkomi.treeisland.plugin.integration.model

import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.kkomi.devlibrary.extension.sendDebugMessage
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.equipitem.model.PlayerEquipItemRepository
import com.kkomi.treeisland.plugin.equipitem.model.entity.PlayerEquipItem
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.PlayerGuild
import com.kkomi.treeisland.plugin.level.model.LevelConfigRepository
import com.kkomi.treeisland.plugin.level.model.PlayerLevelRepository
import com.kkomi.treeisland.plugin.level.model.entity.PlayerLevel
import com.kkomi.treeisland.plugin.money.model.PlayerMoneyRepository
import com.kkomi.treeisland.plugin.money.model.entity.PlayerMoney
import com.kkomi.treeisland.plugin.quest.model.PlayerQuestRepository
import com.kkomi.treeisland.plugin.quest.model.entity.PlayerQuest
import com.kkomi.treeisland.plugin.role.model.PlayerRoleRepository
import com.kkomi.treeisland.plugin.role.model.entity.PlayerRole
import com.kkomi.treeisland.plugin.skill.model.PlayerSkillInfoRepository
import com.kkomi.treeisland.plugin.skill.model.entity.PlayerSkillInfo
import com.kkomi.treeisland.plugin.stat.model.PlayerStatRepository
import com.kkomi.treeisland.plugin.stat.model.entity.PlayerStat
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

class PlayerInfo(
        val player: Player
) {
    private val uuid: String = player.uniqueId.toString()
    val moneyInfo: PlayerMoney
        get() {
            val playerMoney = PlayerMoneyRepository.getPlayerMoney(uuid)
            if (playerMoney == null) {
                PlayerMoneyRepository.createPlayerMoney(PlayerMoney(uuid, 0))
                return PlayerMoneyRepository.getPlayerMoney(uuid)!!
            }
            return playerMoney
        }

    val questInfo: PlayerQuest
        get() {
            val playerQuest = PlayerQuestRepository.getPlayerQuest(uuid)
            if (playerQuest == null) {
                PlayerQuestRepository.createPlayerQuest(PlayerQuest(uuid, mutableListOf(), mutableMapOf()))
                return PlayerQuestRepository.getPlayerQuest(uuid)!!
            }
            return playerQuest
        }

    val statInfo: PlayerStat
        get() {
            val playerSTat = PlayerStatRepository.getPlayerStat(uuid)
            if (playerSTat == null) {
                PlayerStatRepository.createPlayerStat(uuid)
                return PlayerStatRepository.getPlayerStat(uuid)!!
            }
            return playerSTat
        }

    val levelInfo: PlayerLevel
        get() {
            val playerLevel = PlayerLevelRepository.getPlayerLevel(uuid)
            if (playerLevel == null) {
                PlayerLevelRepository.createPlayerLevel(PlayerLevel(uuid, 0, 0))
                return PlayerLevelRepository.getPlayerLevel(uuid)!!
            }
            return playerLevel
        }

    val guildInfo: PlayerGuild
        get() {
            val playerGuild = PlayerGuildRepository.getPlayerGuild(uuid)
            if (playerGuild == null) {
                PlayerGuildRepository.createPlayerGuild(PlayerGuild(uuid, ""))
                return PlayerGuildRepository.getPlayerGuild(uuid)!!
            }
            return playerGuild
        }

    val equipmentInfo: PlayerEquipItem
        get() {
            val playerEquipItem = PlayerEquipItemRepository.getPlayerEquipItem(uuid)
            if (playerEquipItem == null) {
                PlayerEquipItemRepository.getPlayerEquipItem(uuid)
                return PlayerEquipItemRepository.getPlayerEquipItem(uuid)!!
            }
            return playerEquipItem
        }

    val skillInfo: PlayerSkillInfo
        get() {
            val playerSkillInfo = PlayerSkillInfoRepository.getPlayerSkillInfo(uuid)
            if (playerSkillInfo == null) {
                PlayerSkillInfoRepository.createPlayerSkillInfo(PlayerSkillInfo(uuid, false, mutableListOf()))
                return PlayerSkillInfoRepository.getPlayerSkillInfo(uuid)!!
            }
            return playerSkillInfo
        }

    val roleInfo: PlayerRole
        get() {
            val playerRole = PlayerRoleRepository.getPlayerRole(uuid)
            if (playerRole == null) {
                PlayerRoleRepository.createPlayerRole(PlayerRole(uuid, "초보자"))
                return PlayerRoleRepository.getPlayerRole(uuid)!!
            }
            return playerRole
        }

    fun editPlayerInfo() {
        PlayerMoneyRepository.editPlayerMoney(moneyInfo)
        PlayerQuestRepository.editPlayerQuest(questInfo)
        PlayerStatRepository.editPlayerStat(statInfo)
        PlayerLevelRepository.editPlayerLevel(levelInfo)
        PlayerEquipItemRepository.editPlayerEquipItem(equipmentInfo)
        PlayerSkillInfoRepository.editPlayerSkillInfo(skillInfo)
        PlayerRoleRepository.editPlayerRole(roleInfo)
    }

    fun showActionBar() {
        val message = "&cHP %d/%d    &a직업 %s    &6Lv.%d (%.2f%%)    &9MP %d/%d".replaceChatColorCode().format(
                player.health.toInt(),
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).baseValue.toInt(),
                roleInfo.roleName,
                levelInfo.level,
                levelInfo.exp.toDouble() / LevelConfigRepository.getLevelConfig().getExpByLevel(levelInfo.level) * 100,
                20,
                20
        )
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message))
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