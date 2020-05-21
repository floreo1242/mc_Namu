package com.namu.core.rpg.calculate.statusbar

import com.kkomi.devlibrary.extension.replaceChatColorCode
import com.namu.core.rpg.calculate.model.PlayerStatusRepository
import com.namu.core.rpg.level.model.LevelConfigRepository
import com.namu.core.rpg.level.util.playerLevel
import com.namu.core.rpg.mana.util.playerMana
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

object BasicStatusBar {

    fun show(player: Player) {

        val playerStatus = PlayerStatusRepository.getPlayerStatus(player)

        val message = "&c&lHP &c%d/%d     &e&lLv.%d &e%.2f%%     &9&lMP &9%d/%d".format(
                player.health.toInt(),
                playerStatus.maxHealth,
                player.playerLevel.level,
                player.playerLevel.exp.toFloat() / LevelConfigRepository.getLevelConfig().levelExpTable[player.playerLevel.level - 1] * 100,
                player.playerMana.mana,
                playerStatus.maxMana
        ).replaceChatColorCode()

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(message))
    }

}