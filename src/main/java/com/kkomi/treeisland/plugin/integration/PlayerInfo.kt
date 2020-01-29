package com.kkomi.treeisland.plugin.integration

import com.kkomi.treeisland.library.extension.replaceChatColorCode
import com.kkomi.treeisland.library.extension.sendDebugMessage
import com.kkomi.treeisland.library.extension.sendErrorMessage
import com.kkomi.treeisland.library.extension.sendInfoMessage
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
    val moneyInfo: PlayerMoney = PlayerMoneyRepository.getPlayerMoney(uuid)!!
    val questInfo: PlayerQuest = PlayerQuestRepository.getPlayerQuest(uuid)!!
    val statInfo: PlayerStat = PlayerStatRepository.getPlayerStat(uuid)!!
    val levelInfo: PlayerLevel = PlayerLevelRepository.getPlayerLevel(uuid)!!
    val guildInfo: PlayerGuild = PlayerGuildRepository.getPlayerGuild(uuid)!!
    val equipmentInfo: PlayerEquipItem = PlayerEquipItemRepository.getPlayerEquipItem(uuid)!!
    val skillInfo: PlayerSkillInfo = PlayerSkillInfoRepository.getPlayerSkillInfo(uuid)!!
    val roleInfo: PlayerRole = PlayerRoleRepository.getPlayerRole(uuid)!!

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