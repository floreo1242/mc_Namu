package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildOptionRepository
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.integration.model.getPlayerInfo
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

        if (target == null) {
            player.sendErrorMessage("해당 플레이어를 찾을 수 없습니다.")
            return true
        }

        if (player.getPlayerInfo().guildInfo.guildName != target.getPlayerInfo().guildInfo.guildName) {
            player.sendErrorMessage("길드원이 아닙니다.")
            return true
        }

        val upgradeValue = GuildOptionRepository.getUpgradeValue(guild.level)

        if (guild.guildPoint < upgradeValue.guildPoint) {
            player.sendErrorMessage("길드 포인트가 부족합니다.")
            return true
        }

        if (player.getPlayerInfo().moneyInfo.money < upgradeValue.money) {
            player.sendErrorMessage("소지 금액이 부족합니다.")
            return true
        }

        guild.level++
        GuildRepository.editGuild(guild)
        player.sendInfoMessage("길드 레벨업을 하였습니다.")
        return true
    }
}
