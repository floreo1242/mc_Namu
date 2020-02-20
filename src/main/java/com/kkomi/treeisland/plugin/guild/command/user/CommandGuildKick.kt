package com.kkomi.treeisland.plugin.guild.command.user

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.extension.sendErrorMessage
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.kkomi.treeisland.plugin.guild.command.GuildCommandComponent
import com.kkomi.treeisland.plugin.guild.model.GuildRepository
import com.kkomi.treeisland.plugin.guild.model.PlayerGuildRepository
import com.kkomi.treeisland.plugin.guild.model.entity.Guild
import com.kkomi.treeisland.plugin.integration.getPlayerInfo
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.entity.Player

class CommandGuildKick : GuildCommandComponent() {
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

        guild.members.remove(target.uniqueId.toString())
        GuildRepository.editGuild(guild)
        PlayerGuildRepository.editPlayerGuild(target.getPlayerInfo().guildInfo.apply { guildName = "" })

        player.sendInfoMessage("해당 플레이어를 길드에서 추방하였습니다.")
        if (target.isOnline) {
            (target as Player).sendInfoMessage("길드에서 추방당하였습니다.")
        }
        return true
    }
}
