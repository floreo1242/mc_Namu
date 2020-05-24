package com.namu.core.rpg.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.economy.money.util.playerMoney
import com.namu.core.rpg.guild.command.GuildCommandComponent
import com.namu.core.rpg.guild.model.GuildConfigRepository
import com.namu.core.rpg.guild.model.GuildRepository
import com.namu.core.rpg.guild.model.entity.Guild
import com.namu.core.rpg.guild.model.entity.GuildConfig
import com.namu.core.rpg.guild.model.entity.GuildGrade
import com.namu.core.rpg.guild.util.playerGuild
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandUpgradeGuild : GuildCommandComponent() {
    override val argumentsLength: Int = 1

    override val description: String = "길드의 등급을 한단계 올립니다."

    override val usage: String = "<GuildName>"

    override fun onCommand(player: Player, label: String, command: Command, componentLabel: String, args: ArgumentList, guild: Guild): Boolean {
        val targetName = args.next()
        val target = Bukkit.getOfflinePlayer(targetName)

        if (guild.members[player.uniqueId.toString()]!!.grade == GuildGrade.DEPUTY_MANAGER) {
            player.sendErrorMessage("길드 관리자가 아닙니다.")
            return true
        }

        if (player.playerGuild.guildName != target.playerGuild?.guildName) {
            player.sendErrorMessage("길드원이 아닙니다.")
            return true
        }

        val upgradeValue = GuildConfigRepository.getUpgradeValue(guild.level)

        if (GuildConfigRepository.getGuildMaxLevel() == guild.level) {
            player.sendErrorMessage("더 이상 길드를 레벨업 할 수 없습니다.")
            return true
        }

        if (guild.guildPoint < upgradeValue.guildPoint) {
            player.sendErrorMessage("길드 포인트가 부족합니다.")
            return true
        }

        if (player.playerMoney.money < upgradeValue.money) {
            player.sendErrorMessage("소지 금액이 부족합니다.")
            return true
        }

        guild.level++
        GuildRepository.editGuild(guild)
        player.sendInfoMessage("길드 레벨업을 하였습니다.")
        return true
    }
}
